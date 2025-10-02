package com.votrihieu.web26t9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
    }
}