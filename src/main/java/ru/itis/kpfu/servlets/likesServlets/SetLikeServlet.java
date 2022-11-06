package ru.itis.kpfu.servlets.likesServlets;

import ru.itis.kpfu.models.Like;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.LikesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post/setLike")
public class SetLikeServlet extends HttpServlet {
    private LikesService likesService;

    @Override
    public void init() throws ServletException {
        this.likesService = (LikesService) getServletContext().getAttribute("likesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }

        try {
            String query = req.getParameter("postId");
            Long postId = Long.parseLong(query);

            Like newLike = Like.builder()
                    .userId(user.getId())
                    .postId(postId)
                    .build();
            if (likesService.isExist(newLike)) {
                return;
            }

            likesService.addLike(newLike);
//            resp.sendRedirect(req.getContextPath() + "/post/likes?postId=" + postId);
        } catch (NumberFormatException ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
