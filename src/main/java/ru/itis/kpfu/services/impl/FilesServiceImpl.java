package ru.itis.kpfu.services.impl;

import ru.itis.kpfu.models.ImgInfo;
import ru.itis.kpfu.repositories.ImgInfoRepository;
import ru.itis.kpfu.services.FilesService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FilesServiceImpl implements FilesService {
//    private String storagePath = "C:\\Users\\oinuritto\\IdeaProjects\\PostHub\\files\\";
    private final String storagePath;
    private final ImgInfoRepository imgInfoRepository;

    public FilesServiceImpl(String storagePath, ImgInfoRepository imgInfoRepository) {
        this.storagePath = storagePath;
        this.imgInfoRepository = imgInfoRepository;
    }

    @Override
    public ImgInfo upload(Part filePart) {
        try {
            String originalFileName = filePart.getSubmittedFileName();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String storageFileName = UUID.randomUUID() + extension;

            ImgInfo file = ImgInfo.builder()
                    .type(filePart.getContentType())
                    .originalFileName(originalFileName)
                    .storageFileName(storageFileName)
                    .size(filePart.getSize())
                    .build();

            Files.copy(filePart.getInputStream(), Paths.get(storagePath, file.getStorageFileName()));

            return imgInfoRepository.save(file);
        } catch (IOException ex) {
            throw new RuntimeException("Cant save file", ex);
        }
    }

    @Override
    public void addFileToResponseWithFileName(String fileName, HttpServletResponse response) {
        ImgInfo file = imgInfoRepository.findByStorageFileName(fileName)
                .orElseThrow(IllegalArgumentException::new);

        response.setContentLength(file.getSize().intValue());
        response.setContentType(file.getType());
        response.setHeader("Content-Disposition", "filename=\"" + file.getOriginalFileName() + "\"");
        try {
            String path = storagePath + "\\" + file.getStorageFileName();
            Files.copy(Paths.get(path), response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            throw new IllegalArgumentException("Cant read file", ex);
        }
    }

    @Override
    public void addFileToResponseWithId(Long id, HttpServletResponse response) {
        String fileName = imgInfoRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new).getStorageFileName();
        addFileToResponseWithFileName(fileName, response);
    }

}
