package ru.itis.kpfu.servlets.userServlets;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
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
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/registrationPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            registerUser(req);

            resp.sendRedirect(req.getContextPath() + "/auth?message=Successful registration");
        } catch (IllegalArgumentException ex) {
            resp.sendRedirect(req.getContextPath() + "/register?message=" + ex.getMessage());
        }
    }

    private void registerUser(HttpServletRequest req) throws IllegalArgumentException {
        if (req.getParameter("policy") == null) {
            throw new IllegalArgumentException("Privacy policy wasn't agreed.");
        }

        User user = User.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .username(req.getParameter("username"))
                .password(req.getParameter("password"))
                .build();

        // if username is registered (saved at database) - throw IllegalArgumentException
        if (usersService.isRegisteredUser(user)) {
            throw new IllegalArgumentException("This username is already registered");
        }

        // sign up user
        System.out.println(user);
        usersService.signUp(user);
    }
}
