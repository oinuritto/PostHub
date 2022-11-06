package ru.itis.kpfu.repositories;

import ru.itis.kpfu.models.Like;

import java.util.List;
import java.util.Optional;

public interface LikesRepository {
    void save(Like like);

    List<Like> findByPostId(Long id);

    Optional<Like> find(Like like);

    void delete(Like like);
}
