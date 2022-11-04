package ru.itis.kpfu.servlets;

import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.repositories.PostsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post")
public class PostsServlet extends HttpServlet {
    private PostsRepository postsRepository;

    @Override
    public void init() throws ServletException {
        // init postsRepository
        this.postsRepository = (PostsRepository) getServletContext().getAttribute("postsRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null && !req.getParameter("id").equals("")) {
            try {
                Long id = Long.parseLong(req.getParameter("id"));
                Post post = postsRepository.findById(id).orElseThrow(IllegalArgumentException::new);
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
