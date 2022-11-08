package ru.itis.kpfu.services;

import ru.itis.kpfu.models.Post;

import java.util.List;

public interface PostsService {
    List<Post> getAllPosts();

    List<Post> getPage(int page);

    void addPostWithImg(Post post);

    void addPostWithoutImg(Post post);

    Post getPostById(Long id);

    List<Post> getAllPostsLikeTitle(String title);

    boolean deletePost(Long id);
}
