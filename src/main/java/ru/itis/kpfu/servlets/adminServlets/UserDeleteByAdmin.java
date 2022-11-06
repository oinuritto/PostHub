package ru.itis.kpfu.servlets.adminServlets;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminPanel/deleteUser")
public class UserDeleteByAdmin extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        this.usersService = (UsersService) getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("isDeleteAccepted").equals("true")) {
            User deletingUser = usersService.getRegisteredUserByUsername(req.getParameter("deletingUserUsername"));
            usersService.deleteUser(deletingUser);
            resp.sendRedirect(req.getContextPath() + "/adminPanel?message=Success");
        }
    }
}
