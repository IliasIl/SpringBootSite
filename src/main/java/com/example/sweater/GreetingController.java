package com.example.sweater;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingController {
    @GetMapping("/")
    public String getStart(Map<String, Object> model){
        model.put("name", "Ilias");
        return "main";
    }
}
