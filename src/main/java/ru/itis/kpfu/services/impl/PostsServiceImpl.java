package ru.itis.kpfu.services.impl;

import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.repositories.PostsRepository;
import ru.itis.kpfu.services.FilesService;
import ru.itis.kpfu.services.PostsService;

import java.util.List;

public class PostsServiceImpl implements PostsService {
    private final PostsRepository postsRepository;
    private final FilesService filesService;

    public PostsServiceImpl(PostsRepository postsRepository, FilesService filesService) {
        this.postsRepository = postsRepository;
        this.filesService = filesService;
    }

    @Override
    public List<Post> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public List<Post> getPage(int page) {
        int limit = 5;
        int offset = (page - 1) * limit;
        return postsRepository.findAllByOffsetAndLimit(offset, limit);
    }

    @Override
    public void addPostWithImg(Post post) {
        postsRepository.saveWithImg(post);
    }

    @Override
    public void addPostWithoutImg(Post post) {
        postsRepository.saveWithoutImg(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post with this id doesn't exist"));
    }

    @Override
    public List<Post> getAllPostsLikeTitle(String title) {
        return postsRepository.findAllLikeTitle(title);
    }

    @Override
    public boolean deletePost(Long id) {
        Long imgId = getPostById(id).getImgId();
        if (postsRepository.delete(id) == 1) {
            return filesService.delete(imgId);
        }
        return false;
    }
}
