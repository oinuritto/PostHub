package ru.itis.kpfu.servlets;

import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.repositories.PostsRepository;
import ru.itis.kpfu.services.PostsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class MainPageServlet extends HttpServlet {
    private PostsService postsService;

    @Override
    public void init() throws ServletException {
        // init postsService
        this.postsService = (PostsService) getServletContext().getAttribute("postsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get the page Number
        Integer pageNum = getPageNum(req, resp);
        if (pageNum == null) return;

        // show the page
        showPage(req, resp, pageNum);
    }

    private void showPage(HttpServletRequest req, HttpServletResponse resp, Integer pageNum) throws ServletException, IOException {
        if (req.getParameter("query") == null || req.getParameter("query").equals("")) {
            System.out.println(pageNum);
            List<Post> posts = postsService.getPage(pageNum);
            System.out.println(posts);
            req.setAttribute("posts", posts);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

        } else {
            String title = req.getParameter("query");
            List<Post> posts = postsService.getAllPostsLikeTitle(title);
            req.setAttribute("posts", posts);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        }
    }

    private static Integer getPageNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int pageNum;
        try {
            pageNum = Integer.parseInt(req.getParameter("page"));

            if (pageNum <= 0) {
                resp.sendRedirect(req.getContextPath() + "/?page=1");
                return null;
            }
        } catch (NumberFormatException ex) {
            resp.sendRedirect(req.getContextPath() + "/?page=1");
            return null;
        }
        req.setAttribute("page", pageNum);
        return pageNum;
    }
}
