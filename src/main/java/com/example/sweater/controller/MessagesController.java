package com.example.sweater.controller;

import com.example.sweater.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
