package ru.itis.kpfu.services.impl;

import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.repositories.PostsRepository;
import ru.itis.kpfu.services.PostsService;

import java.util.List;

public class PostsServiceImpl implements PostsService {
    private final PostsRepository postsRepository;

    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public void addPost(Post post) {
        postsRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post with this id doesn't exist"));
    }

    @Override
    public List<Post> getAllPostsLikeTitle(String title) {
        return postsRepository.findAllLikeTitle(title);
    }
}
