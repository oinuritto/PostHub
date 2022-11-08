package ru.itis.kpfu.servlets.postsServlets;

import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post/delete")
public class DeletePostServlet extends HttpServlet {
    private PostsService postsService;

    @Override
    public void init() throws ServletException {
        // init postsService
        this.postsService = (PostsService) getServletContext().getAttribute("postsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null && !req.getParameter("id").equals("")) {
            try {
                Long id = Long.parseLong(req.getParameter("id"));
                User user = (User) req.getSession().getAttribute("user");

                Post post = postsService.getPostById(id);

                if (user.getId().equals(post.getUserId()) || user.getRole() == User.ROLE.admin) {
                    if (!postsService.deletePost(id)) {
                        resp.sendRedirect(req.getContextPath() + "/post?id=" + id + "&message=Cannot delete this post");
                        return;
                    }
                }

                resp.sendRedirect(req.getContextPath());
            } catch (IllegalArgumentException ex) {
                resp.sendRedirect(req.getContextPath());
            }
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }
}
