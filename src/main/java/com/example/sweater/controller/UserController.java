package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String editPage(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String addUser(@RequestParam("userId") User user,
                          @RequestParam String username,
                          @RequestParam Map<String, String> form) {

        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("profile/{userFromPath}")
    public String getProf(Model model,
                          @AuthenticationPrincipal User user,
                          @PathVariable User userFromPath) {
        model.addAttribute("username", userFromPath.getUsername());
        model.addAttribute("email", userFromPath.getEmail());
        model.addAttribute("user", userFromPath);
        log.info("getmapping send+ {}", userFromPath.getEmail());
        return "profile";
    }

    @PostMapping("profile/{user_id}")
    public String updateAccount(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email,
                                @PathVariable("user_id") User userFromPath) {

        userService.updateAccount(userFromPath, password, email);

        return "redirect:/user/profile/" + userFromPath.getId();
    }

    @GetMapping("/{type}/{user}")
    public String plain(@AuthenticationPrincipal User currentUser,
                        @PathVariable User user,
                        @PathVariable String type) {
        if (type.equals("subscribe")) {
            userService.subscribe(currentUser, user);
        } else if (type.equals("unsubscribe")) {
            userService.unsubscribe(currentUser, user);
        }
        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("/{type}/{user}/list")
    public String returnSub(@PathVariable String type,
                            @PathVariable User user,
                            Model model) {
        if (type.equals("subscriptions")) {
            model.addAttribute("users", user.getSubscription());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }

        model.addAttribute("type", type);
        model.addAttribute("userCur", user);

        return "subscription";
    }
}


