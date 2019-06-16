package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessagesController {
    @GetMapping("user-messages/{user}")
    public String allMessage(@AuthenticationPrincipal User currentUser,
                             @PathVariable User user,
                             Model model) {
        model.addAttribute("isCurrent", currentUser.getId().equals(user.getId()));
        model.addAttribute("messages", user.getMessages());
        return "userMessages";
    }

    @PostMapping("user-messages/{user}")
    public String editMessage(@AuthenticationPrincipal User currentUser,
                              @PathVariable User user,
                              @RequestParam(required = false) Message message) {
        return "userMessages";
    }
}
