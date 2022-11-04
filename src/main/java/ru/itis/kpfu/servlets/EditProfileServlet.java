package ru.itis.kpfu.servlets;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;
import ru.itis.kpfu.utils.UserPasswordHasher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile/editProfile")
public class EditProfileServlet extends HttpServlet {
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
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/userEditProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/profile/editProfile?message=You didn't give an argument");
            return;
        }
        User user = (User) req.getSession().getAttribute("user");

        editUserProfile(req, resp, user);
    }


    private void editUserProfile(HttpServletRequest req, HttpServletResponse resp, User user) throws IOException {
        String newUsername = req.getParameter("username");
        if (newUsername != null && !newUsername.equals("")) {
            user.setUsername(newUsername);
            usersService.update(user);
            resp.sendRedirect(req.getContextPath() + "/profile/editProfile?message=Success");
        }

        String newFirstName = req.getParameter("firstName");
        if (newFirstName != null && !newFirstName.equals("")) {
            user.setFirstName(newFirstName);
            usersService.update(user);
            resp.sendRedirect(req.getContextPath() + "/profile/editProfile?message=Success");
        }

        String newLastName = req.getParameter("lastName");
        if (newLastName != null && !newLastName.equals("")) {
            user.setLastName(newLastName);
            usersService.update(user);
            resp.sendRedirect(req.getContextPath() + "/profile/editProfile?message=Success");
        }

        String newPassword = req.getParameter("password");
        if (newPassword != null && !newPassword.equals("")) {
            // check if real old password equals written in form old password
            if (UserPasswordHasher.getHashedPassword(req.getParameter("oldPassword")).equals(user.getPassword())) {
                user.setPassword(newPassword);
                usersService.update(user, true);
                resp.sendRedirect(req.getContextPath() + "/profile/editProfile?message=Success");
            } else {
                resp.sendRedirect(req.getContextPath() + "/profile/editProfile?message=Wrong old password");
            }
        }
    }
}
