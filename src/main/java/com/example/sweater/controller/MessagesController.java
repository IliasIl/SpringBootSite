package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.domain.dto.MessageDto;
import com.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Set;

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
        Page<MessageDto> page = messageService.findAllByUser(pageable, user, currentUser);
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

    @GetMapping("/messages/{message}/like")
    public String likedAdd(@PathVariable Message message,
                           @AuthenticationPrincipal User currentUser,
                           RedirectAttributes redirectAttributes,
                           @RequestHeader(required = false) String referer) {

        Set<User> likes = message.getLikes();
        if (likes.contains(currentUser)) {
            likes.remove(currentUser);
        } else {
            likes.add(currentUser);
        }
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .entrySet()
                .forEach(a -> redirectAttributes.addAttribute(a.getKey(), a.getValue()));
        return "redirect:"+components.getPath();
    }


}
