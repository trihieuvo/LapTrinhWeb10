package com.votrihieu.web26t9.controller;

import com.votrihieu.web26t9.model.User;
import com.votrihieu.web26t9.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Trả về trang login.html
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Lưu thông tin người dùng vào session
            session.setAttribute("loggedInUser", user);

            // Phân luồng dựa trên vai trò
            if (user.getRole() == com.votrihieu.web26t9.model.Role.ADMIN) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/user/home";
            }
        } else {
            // Thêm thông báo lỗi và redirect về trang login
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Hủy session
        return "redirect:/login";
    }
}