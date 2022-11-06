package ru.itis.kpfu.servlets.likesServlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.kpfu.models.Like;
import ru.itis.kpfu.services.LikesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/post/likes")
public class LikesServlet extends HttpServlet {
    private LikesService likesService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        this.likesService = (LikesService) getServletContext().getAttribute("likesService");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String query = req.getParameter("postId");
            Long postId = Long.parseLong(query);
            List<Like> likes = likesService.getByPostId(postId);
            String jsonResponse = objectMapper.writeValueAsString(likes);
            resp.setContentType("application/json");
            resp.getWriter().println(jsonResponse);
        } catch (NumberFormatException ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
