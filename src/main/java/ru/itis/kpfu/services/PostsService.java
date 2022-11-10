package ru.itis.kpfu.services;

import ru.itis.kpfu.models.Post;

import java.util.List;

public interface PostsService {
    List<Post> getAllPosts();

    List<Post> getAllPostByUserId(Long id);

    List<Post> getPage(int page, int postsInPage);

    List<Post> getPage(int page, int postsInPage, boolean sortedByLikesCountDesc);

    void addPostWithImg(Post post);

    void addPostWithoutImg(Post post);

    Post getPostById(Long id);

    List<Post> getAllPostsLikeTitle(String title);

    boolean deletePost(Long id);
}
