package ru.itis.kpfu.services;

import ru.itis.kpfu.models.Post;

import java.util.List;

public interface PostsService {
    List<Post> getAllPosts();

    void addPost(Post post);

    Post getPostById(Long id);

    List<Post> getAllPostsLikeTitle(String title);
}
