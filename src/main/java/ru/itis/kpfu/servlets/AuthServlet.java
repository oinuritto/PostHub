package ru.itis.kpfu.servlets;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // init usersService
        this.usersService = (UsersService) getServletContext().getAttribute("usersService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("message") != null)  {
            req.setAttribute("message", req.getParameter("message"));
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/authPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = usersService.getRegisteredUserByUsernamePassword(username, password);
            httpSession.setAttribute("user", user);

            resp.sendRedirect(req.getContextPath() + "/profile");
        } catch (IllegalArgumentException ex) {
            resp.sendRedirect(req.getContextPath() + "/auth?message=" + ex.getMessage());
        }
    }
}
