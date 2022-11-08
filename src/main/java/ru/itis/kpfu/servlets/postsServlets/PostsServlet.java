package ru.itis.kpfu.servlets.postsServlets;

import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.services.LikesService;
import ru.itis.kpfu.services.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet("/post")
public class PostsServlet extends HttpServlet {
    private PostsService postsService;
    private LikesService likesService;

    @Override
    public void init() throws ServletException {
        // init postsRepository
        this.postsService = (PostsService) getServletContext().getAttribute("postsService");
        this.likesService = (LikesService) getServletContext().getAttribute("likesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null && !req.getParameter("id").equals("")) {
            try {
                Long id = Long.parseLong(req.getParameter("id"));
                Post post = postsService.getPostById(id);
                likesService.setLikesToPosts(Collections.singletonList(post));

                req.setAttribute("post", post);
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/post.jsp").forward(req, resp);
            } catch (IllegalArgumentException ex) {
                resp.sendRedirect(req.getContextPath());
            }
        } else {
            resp.sendRedirect(req.getContextPath());
        };
    }
}
