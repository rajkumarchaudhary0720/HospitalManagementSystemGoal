package com.hospital.filter;

import com.hospital.model.Role;
import com.hospital.model.User;
import com.hospital.util.SessionUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/receptionist/*", "/dashboard"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(javax.servlet.ServletRequest servletRequest,
                         javax.servlet.ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = SessionUtil.getLoggedInUser(request);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (path.startsWith("/admin/") && user.getRole() != Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/receptionist/dashboard");
            return;
        }

        if (path.startsWith("/receptionist/") && user.getRole() != Role.RECEPTIONIST) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }
}
