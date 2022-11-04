package ru.itis.kpfu.filters;

import ru.itis.kpfu.models.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/adminPanel/*")
public class AdminPanelFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();

        if (session == null ||
                session.getAttribute("user") == null ||
                ((User) session.getAttribute("user")).getRole() != User.ROLE.admin) {
            res.sendRedirect(req.getContextPath());
            return;
        }
        chain.doFilter(req, res);
    }
}
