package ru.itis.kpfu.repositories;

import ru.itis.kpfu.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostsRepository {
    List<Post> findAll();

    List<Post> findAllByOffsetAndLimit(int offset, int limit);

    List<Post> findAllByOffsetAndLimitSortedByLikesCount(int offset, int limit);

    void saveWithImg(Post post);

    void saveWithoutImg(Post post);

    Optional<Post> findById(Long id);

    List<Post> findAllLikeTitle(String title);

    int delete(Long id);
}
