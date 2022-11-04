package ru.itis.kpfu.services;

import ru.itis.kpfu.models.ImgInfo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public interface FilesService {
    ImgInfo upload(Part filePart);

    void addFileToResponseWithFileName(String fileName, HttpServletResponse response);

    void addFileToResponseWithId(Long id, HttpServletResponse response);
}
