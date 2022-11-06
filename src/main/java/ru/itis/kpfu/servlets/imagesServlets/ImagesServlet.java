package ru.itis.kpfu.servlets.imagesServlets;

import ru.itis.kpfu.services.FilesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/images")
public class ImagesServlet extends HttpServlet {
    private FilesService filesService;

    @Override
    public void init() throws ServletException {
        this.filesService = (FilesService) getServletContext().getAttribute("filesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String storageFileName = req.getParameter("storageFileName");


        try {
            Long id = Long.parseLong(req.getParameter("id"));
//            filesService.addFileToResponseWithFileName(storageFileName, resp);
            filesService.addFileToResponseWithId(id, resp);
        } catch (IllegalArgumentException ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
