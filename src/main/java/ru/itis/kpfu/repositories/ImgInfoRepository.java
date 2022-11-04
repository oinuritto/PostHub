package ru.itis.kpfu.repositories;

import ru.itis.kpfu.models.ImgInfo;

import java.util.List;
import java.util.Optional;

public interface ImgInfoRepository {
    ImgInfo save(ImgInfo imgInfo);

    List<ImgInfo> findAll();

    Optional<ImgInfo> findByStorageFileName(String name);

    Optional<ImgInfo> findById(Long id);
}
