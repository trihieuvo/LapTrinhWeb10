package com.votrihieu.web26t9.interceptor;

import com.votrihieu.web26t9.model.Role;
import com.votrihieu.web26t9.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();

        // Lấy thông tin người dùng từ session
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // === Logic cho các trang Admin ===
        if (requestURI.startsWith("/admin")) {
            // 1. Nếu chưa đăng nhập -> chuyển về trang login
            if (loggedInUser == null) {
                response.sendRedirect("/login");
                return false; // Ngăn không cho request tiếp tục
            }
            // 2. Nếu đã đăng nhập nhưng không phải ADMIN -> chuyển về trang login
            if (loggedInUser.getRole() != Role.ADMIN) {
                // Có thể tạo một trang "Access Denied" (403) nhưng để đơn giản, ta chuyển về login
                response.sendRedirect("/login");
                return false; // Ngăn không cho request tiếp tục
            }
        }

        // === Logic cho các trang User ===
        if (requestURI.startsWith("/user")) {
            // Chỉ cần kiểm tra đã đăng nhập là được (cả USER và ADMIN đều có thể vào)
            if (loggedInUser == null) {
                response.sendRedirect("/login");
                return false;
            }
        }
        
        // Nếu không thuộc các trường hợp trên (ví dụ: truy cập trang login, logout...), cho phép request tiếp tục
        return true;
    }
}