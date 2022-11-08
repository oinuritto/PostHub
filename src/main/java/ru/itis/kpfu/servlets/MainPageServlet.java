package ru.itis.kpfu.servlets;

import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.services.LikesService;
import ru.itis.kpfu.services.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// TODO: сделать удаление постов\
// TODO: по возможности комментарии

@WebServlet("/")
public class MainPageServlet extends HttpServlet {
    private PostsService postsService;
    private LikesService likesService;

    @Override
    public void init() throws ServletException {
        // init postsService
        this.postsService = (PostsService) getServletContext().getAttribute("postsService");
        // init likesService
        this.likesService = (LikesService) getServletContext().getAttribute("likesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("query") == null || req.getParameter("query").equals("")) {
            // get the page Number
            Integer pageNum = getPageNum(req, resp);
            if (pageNum == null) return;

            // show the page for the request page
            calcPage(req, resp, pageNum);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        }

        // show the page for search request
        calcPage(req, resp, 0);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }

    private void calcPage(HttpServletRequest req, HttpServletResponse resp, Integer pageNum) throws ServletException, IOException {
        if (pageNum != 0) {
            List<Post> posts = postsService.getPage(pageNum);
            setLikesAndAttributes(req, posts);

        } else {
            String title = req.getParameter("query");
            List<Post> posts = postsService.getAllPostsLikeTitle(title);
            setLikesAndAttributes(req, posts);

        }
    }

    private void setLikesAndAttributes(HttpServletRequest req, List<Post> posts) {
        likesService.setLikesToPosts(posts);

        List<Long> idOfPosts = posts.stream()
                .map((Post::getId)).toList();
        req.setAttribute("idOfPosts", idOfPosts);
        req.setAttribute("posts", posts);
    }

    private static Integer getPageNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int pageNum;
        try {
            pageNum = Integer.parseInt(req.getParameter("page"));

            if (pageNum <= 0) {
                resp.sendRedirect(req.getContextPath() + "?page=1");
                return null;
            }
        } catch (NumberFormatException ex) {
            resp.sendRedirect(req.getContextPath() + "?page=1");
            return null;
        }
        req.setAttribute("page", pageNum);
        return pageNum;
    }
}
