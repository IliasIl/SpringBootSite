package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.domain.dto.MessageDto;
import com.example.sweater.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class MainController {


    @Autowired
    private ControllerUtils controllerUtils;

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String getStart(Map<String, Object> model) {
        return "greeting";
    }


    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<MessageDto> page = messageService.findAll(pageable, filter, user);
        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);
        return "main";

    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer)
            throws IOException {

        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            log.info("errors in if cycle");
            Map<String, String> errorMap = controllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("message", message);
            repeatAdded(user, model, pageable);
            return "main";
        } else {
            controllerUtils.saveImage(message, file);
            model.addAttribute("message", null);
            messageService.save(message);
        }
        repeatAdded(user, model, pageable);
        UriComponents components = controllerUtils.getUriComponents(redirectAttributes, referer);

        return "redirect:" + components.getPath();
    }

    public void repeatAdded(User user, Model model, Pageable pageable) {
        Page<MessageDto> page = messageService.findAll(pageable, user);
        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
    }


}
