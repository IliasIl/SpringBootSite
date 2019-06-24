package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MessagesController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private ControllerUtils controllerUtils;

    @GetMapping("user-messages/{user}")
    public String allMessage(@AuthenticationPrincipal User currentUser,
                             @PathVariable User user,
                             @RequestParam(required = false) Message message,
                             Model model) {
        model.addAttribute("userChannel", user);
        model.addAttribute("isCurrent", currentUser.getId().equals(user.getId()));
        model.addAttribute("message", message);
        model.addAttribute("subscriptionCount",user.getSubscription().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("page", user.getMessages());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("url", "/user-messages/"+user.getId());
        return "userMessages";
    }

    @PostMapping("user-messages/{user}")
    public String editMessage(@AuthenticationPrincipal User currentUser,
                              @PathVariable Long user,
                              @RequestParam("id") Message message,
                              @RequestParam("text") String text,
                              @RequestParam("tag") String tag,
                              @RequestParam("file") MultipartFile file) throws IOException {

        if (currentUser.getId().equals(message.getAuthor().getId())) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }
            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }
            if (!StringUtils.isEmpty(file)) {
                controllerUtils.saveImage(message, file);
            }
            messageRepo.save(message);
        }
        return "redirect:/user-messages/" + user;
    }
}
