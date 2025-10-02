package com.votrihieu.web26t9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @GetMapping("/home")
    public String adminHomePage() {
        return "admin_home"; // Trả về file templates/admin_home.html
    }
}