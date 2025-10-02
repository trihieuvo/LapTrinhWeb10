package com.votrihieu.web26t9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserPageController {

    @GetMapping("/home")
    public String userHomePage() {
        return "user_home"; // Trả về file templates/user_home.html
    }
}