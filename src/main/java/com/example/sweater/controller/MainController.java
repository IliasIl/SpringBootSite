package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;


    @GetMapping("/")
    public String getStart(Map<String, Object> model) {
        model.put("name", "Ilias");
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("messages", messageRepo.findAll());
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        messageRepo.save(new Message(text, tag, user));
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filt, Map<String, Object> model){
        Iterable<Message> messages;
        if(filt!=null && !filt.isEmpty()){
            messages=messageRepo.findByTag(filt);
        } else {
            messages=messageRepo.findAll();
        }
        model.put("messages", messages);

        return "main";
    }
}
