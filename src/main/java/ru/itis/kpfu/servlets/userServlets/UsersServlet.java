package ru.itis.kpfu.servlets.userServlets;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        this.usersService = (UsersService) getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        calcPage(req);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/usersPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (req.getParameter("isSorted") != null && req.getParameter("isSorted").equals("true")) {
            httpSession.setAttribute("isSorted", true);
        } else {
            httpSession.setAttribute("isSorted", false);
        }
        resp.sendRedirect(req.getContextPath() + "/users");
    }

    private void calcPage(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();

        List<User> usersList;
        if (httpSession.getAttribute("isSorted") != null && (Boolean) httpSession.getAttribute("isSorted")) {
            usersList = usersService.getAllUsers(true);
        } else {
            usersList = usersService.getAllUsers();
        }

        req.setAttribute("usersList", usersList);
    }
}
