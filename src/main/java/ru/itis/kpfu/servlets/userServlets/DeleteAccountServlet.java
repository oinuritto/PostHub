package ru.itis.kpfu.servlets.userServlets;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile/delete")
public class DeleteAccountServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        this.usersService = (UsersService) getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("isDeleteAccepted").equals("true")) {
            User user = (User) req.getSession().getAttribute("user");
            usersService.deleteUser(user);
            resp.sendRedirect(req.getContextPath() + "/logout");
        }
    }
}
