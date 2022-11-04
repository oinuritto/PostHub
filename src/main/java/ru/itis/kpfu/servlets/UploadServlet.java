package ru.itis.kpfu.servlets;

import ru.itis.kpfu.models.ImgInfo;
import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.repositories.PostsRepository;
import ru.itis.kpfu.services.FilesService;

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
    private PostsRepository postsRepository;
    private FilesService filesService;

    @Override
    public void init() throws ServletException {
        // init postsRepository
        this.postsRepository = (PostsRepository) getServletContext().getAttribute("postsRepository");
        this.filesService = (FilesService) getServletContext().getAttribute("filesService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");

            String postTitle = req.getParameter("postTitle");
            String postText = req.getParameter("postText");

            ImgInfo imgInfo = filesService.upload(req.getPart("file"));

            Post post = Post.builder()
                    .title(postTitle)
                    .text(postText)
                    .userId(user.getId())
                    .imgId(imgInfo.getId())
//                    .imgStorageFileName(imgInfo.getStorageFileName())
                    .build();

            postsRepository.save(post);
            resp.sendRedirect(req.getContextPath() + "/newpost?message=Success");
        } catch (RuntimeException ex) {
            resp.sendRedirect(req.getContextPath() + "/newpost?message=Can't save the post. Try again...");
            ex.printStackTrace();
        }
    }
}
