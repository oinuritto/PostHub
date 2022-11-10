package ru.itis.kpfu.servlets.postsServlets;

import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.services.LikesService;
import ru.itis.kpfu.services.PostsService;
import ru.itis.kpfu.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users/posts")
public class UserPostsServlet extends HttpServlet {
    private PostsService postsService;
    private LikesService likesService;
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        // init postsService
        this.postsService = (PostsService) getServletContext().getAttribute("postsService");
        // init likesService
        this.likesService = (LikesService) getServletContext().getAttribute("likesService");
        // init usersService
        this.usersService = (UsersService) getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long userId = Long.parseLong(req.getParameter("id"));
            List<Post> posts = postsService.getAllPostByUserId(userId);
            setLikesAndAttributes(req, posts, userId);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/userPosts.jsp").forward(req, resp);
        } catch (NumberFormatException ex) {
            resp.sendRedirect(req.getContextPath() + "/users");
        }

    }

    private void setLikesAndAttributes(HttpServletRequest req, List<Post> posts, Long userId) {
        likesService.setLikesToPosts(posts);

        List<Long> idOfPosts = posts.stream()
                .map((Post::getId)).toList();
        req.setAttribute("idOfPosts", idOfPosts);
        req.setAttribute("posts", posts);
        req.setAttribute("username", usersService.getRegisteredUserById(userId).getUsername());
    }
}
