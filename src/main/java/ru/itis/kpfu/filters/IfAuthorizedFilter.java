package ru.itis.kpfu.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(value = {"/auth", "/register"})
public class IfAuthorizedFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("user"))) {
            res.sendRedirect(req.getContextPath() + "/profile");
            return;
        }
        chain.doFilter(req, res);
    }
}
