package ru.itis.kpfu.repositories;

import ru.itis.kpfu.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostsRepository {
    List<Post> findAll();

    void save(Post post);

    Optional<Post> findById(Long id);

    List<Post> findAllLikeTitle(String title);

//    void update(Post post);

//    Optional<User> findPostByTitle(String title);

//    void delete(Long id);
}