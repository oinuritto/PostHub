package ru.itis.kpfu.servlets.adminServlets;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/adminPanel/*")
public class AdminPanelServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        this.usersService = (UsersService) getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("message") != null)  {
            req.setAttribute("message", req.getParameter("message"));
        }

        List<User> usersList = usersService.getAllUsers();
        usersList.sort(Comparator.comparing(User::getRating).reversed());
        req.setAttribute("usersList", usersList);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/adminPanel.jsp").forward(req, resp);
    }
}
