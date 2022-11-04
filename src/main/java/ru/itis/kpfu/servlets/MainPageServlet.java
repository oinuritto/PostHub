package ru.itis.kpfu.servlets;

import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.repositories.PostsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class MainPageServlet extends HttpServlet {
    private PostsRepository postsRepository;

    @Override
    public void init() throws ServletException {
        // init postsRepository
        this.postsRepository = (PostsRepository) getServletContext().getAttribute("postsRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = postsRepository.findAll();
//        System.out.println(posts.get(0));
        req.setAttribute("posts", posts);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }
}
