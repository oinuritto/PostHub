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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        // init usersService
        this.usersService = (UsersService) getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // update user info (to update user's rate) in session
        User user = (User) req.getSession().getAttribute("user");
        User updatedUser = usersService.getRegisteredUserById(user.getId());
        req.getSession().setAttribute("user", updatedUser);

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

}
