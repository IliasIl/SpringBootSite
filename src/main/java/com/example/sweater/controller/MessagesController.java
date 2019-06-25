package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MessagesController {
    @Autowired
    private MessageService messageService;

    @GetMapping("user-messages/{user}")
    public String allMessage(@AuthenticationPrincipal User currentUser,
                             @PathVariable User user,
                             @RequestParam(required = false) Message message,
                             Model model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Message> page = messageService.findAllByUser(pageable, user);
        model.addAttribute("userChannel", user);
        model.addAttribute("isCurrent", currentUser.getId().equals(user.getId()));
        model.addAttribute("message", message);
        model.addAttribute("subscriptionCount", user.getSubscription().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("page", page);
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("url", "/user-messages/" + user.getId());
        return "userMessages";
    }

    @PostMapping("user-messages/{user}")
    public String editMessage(@AuthenticationPrincipal User currentUser,
                              @PathVariable Long user,
                              @RequestParam("id") Message message,
                              @RequestParam("text") String text,
                              @RequestParam("tag") String tag,
                              @RequestParam("file") MultipartFile file) throws IOException {

        messageService.editMessage(currentUser, message, text, tag, file);
        return "redirect:/user-messages/" + user;
    }


}
