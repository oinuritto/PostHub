package ru.itis.kpfu.servlets.imagesServlets;

import ru.itis.kpfu.models.ImgInfo;
import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.services.FilesService;
import ru.itis.kpfu.services.PostsService;
import ru.itis.kpfu.validation.PostValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class UploadServlet extends HttpServlet {
    private PostsService postsService;
    private FilesService filesService;
    private PostValidator postValidator;

    @Override
    public void init() throws ServletException {
        // init postsRepository
        this.postsService = (PostsService) getServletContext().getAttribute("postsService");
        this.filesService = (FilesService) getServletContext().getAttribute("filesService");
        this.postValidator = new PostValidator();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        String postTitle = req.getParameter("postTitle");
        String postText = req.getParameter("postText");

        Post post = Post.builder()
                .title(postTitle)
                .text(postText)
                .userId(user.getId())
                .imgId(null)
                .build();

        if (req.getPart("file") != null && req.getParameter("file") != null) {
            try {
                ImgInfo imgInfo = filesService.upload(req.getPart("file"));
                post.setImgId(imgInfo.getId());
                postValidator.validate(post);
                postsService.addPostWithImg(post);
                resp.sendRedirect(req.getContextPath() + "/newpost?message=Success");
            } catch (RuntimeException ex) {
                resp.sendRedirect(req.getContextPath() + "/newpost?message=" + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            try {
                postValidator.validate(post);
                postsService.addPostWithoutImg(post);
                resp.sendRedirect(req.getContextPath() + "/newpost?message=Success");
            } catch (RuntimeException ex) {
                resp.sendRedirect(req.getContextPath() + "/newpost?message=" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
