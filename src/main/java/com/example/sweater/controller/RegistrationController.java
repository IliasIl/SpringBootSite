package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.domain.dto.CaptchaResponseDto;
import com.example.sweater.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
@Slf4j
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Autowired
    ControllerUtils controllerUtils;
    @Value("${recaptcha.secret}")
    private String secret;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public String reg() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2") String passwordConfirmation,
                          @RequestParam("g-recaptcha-response") String response,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {

        String url = String.format(CAPTCHA_URL, secret, response);
        CaptchaResponseDto captchaResponseDto = restTemplate.postForObject
                (url, Collections.emptyList(), CaptchaResponseDto.class);
        log.info("Responce: {}",captchaResponseDto.isSuccess());
        if(!captchaResponseDto.isSuccess()){
            model.addAttribute("captchaError", "Fill captcha");
        }

        boolean pasEr = StringUtils.isEmpty(passwordConfirmation);
        if (pasEr) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }

        boolean checkPassword = user.getPassword() != null && !user.getPassword().equals(passwordConfirmation);
        if (checkPassword) {
            model.addAttribute("passwordError", "Passwords are different");
        }

        if (!captchaResponseDto.isSuccess() || checkPassword || pasEr || bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        boolean exist = userService.addUser(user);

        if (!exist) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String getActivate(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("param", "success");
            model.addAttribute("message", "User successfully activated!");
        } else {
            model.addAttribute("param", "danger");
            model.addAttribute("message", "Activation code is not found");
        }
        return "login";
    }

}
