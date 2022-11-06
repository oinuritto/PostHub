package ru.itis.kpfu.servlets.adminServlets;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;
import ru.itis.kpfu.utils.UserPasswordHasher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminPanel/userEdit")
public class UserEditByAdminServlet extends HttpServlet {
    private UsersService usersService;
    private User editingUser;

    @Override
    public void init() throws ServletException {
        this.usersService = (UsersService) getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            editingUser = usersService.getRegisteredUserByUsername(req.getParameter("username"));
            req.setAttribute("editingUser", editingUser);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/userEditByAdmin.jsp").forward(req, resp);
        } catch (IllegalArgumentException ex) {
            resp.sendRedirect(req.getContextPath() + "/adminPanel?message=" + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/adminPanel?message=You didn't give an argument");
            return;
        }

//        editUserProfile(req, resp, editingUser);
        try {
            editUserProfile(req, resp, editingUser);
        } catch (IllegalArgumentException ex) {
            editingUser = usersService.getRegisteredUserById(editingUser.getId());
            resp.sendRedirect(req.getContextPath() + "/adminPanel?username=" + editingUser.getUsername() +
                            "&message=" + ex.getMessage());
            ex.printStackTrace();
        }
    }


    private void editUserProfile(HttpServletRequest req, HttpServletResponse resp, User editingUser) throws IOException {
        String newFirstName = req.getParameter("firstName");
        if (newFirstName != null && !newFirstName.equals("")) {
            editingUser.setFirstName(newFirstName);
            usersService.update(editingUser);
            resp.sendRedirect(req.getContextPath() + "/adminPanel/editUser?username="
                    + editingUser.getUsername() +"&message=Success");
        }

        String newLastName = req.getParameter("lastName");
        if (newLastName != null && !newLastName.equals("")) {
            editingUser.setLastName(newLastName);
            usersService.update(editingUser);
            resp.sendRedirect(req.getContextPath() + "/adminPanel/editUser?username="
                    + editingUser.getUsername() +"&message=Success");
        }
    }
}
