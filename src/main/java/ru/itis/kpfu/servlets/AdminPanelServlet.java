package ru.itis.kpfu.servlets;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        req.setAttribute("usersList", usersService.getAllUsers());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/adminPanel.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getParameter("isDeleteAccepted").equals("true")) {
//            User userToDelete = usersService.getRegisteredUserByUsername(req.getParameter("deletingUser"));
//            usersService.deleteUser(userToDelete);
//            resp.sendRedirect(req.getContextPath() + "/adminPanel?message=Success");
//        } else {
//            resp.sendRedirect(req.getContextPath() + "/adminPanel?message=Something went wrong...");
//        }
//    }
}
