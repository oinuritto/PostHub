package ru.itis.kpfu.servlets.userServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminPanel/searchUsers")
public class UsersSearchServlet extends HttpServlet {
    private UsersService usersService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        this.usersService = (UsersService) getServletContext().getAttribute("usersService");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        List<User> users = usersService.getAllUsersLikeUsername(query);
        String jsonResponse = objectMapper.writeValueAsString(users);
        resp.setContentType("application/json");
        resp.getWriter().println(jsonResponse);
    }
}
