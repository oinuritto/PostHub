package ru.itis.kpfu.services.impl;

import ru.itis.kpfu.models.Like;
import ru.itis.kpfu.models.Post;
import ru.itis.kpfu.models.User;
import ru.itis.kpfu.repositories.LikesRepository;
import ru.itis.kpfu.services.LikesService;
import ru.itis.kpfu.services.UsersService;

import java.util.List;

public class LikesServiceImpl implements LikesService {
    private final LikesRepository likesRepository;
    private final UsersService usersService;

    public LikesServiceImpl(LikesRepository likesRepository, UsersService usersService) {
        this.likesRepository = likesRepository;
        this.usersService = usersService;
    }

    @Override
    public void addLike(Like like) {
        likesRepository.save(like);
        usersService.updateRatingByPostId(like.getPostId(), 1);
    }

    @Override
    public List<Like> getByPostId(Long id) {
        return likesRepository.findByPostId(id);
    }

    @Override
    public boolean isExist(Like like) {
        return likesRepository.find(like).isPresent();
    }

    @Override
    public void removeLike(Like like) {
        likesRepository.delete(like);
        usersService.updateRatingByPostId(like.getPostId(), -1);
    }

    @Override
    public void setLikesToPosts(List<Post> posts) {
        for (Post post : posts) {
            post.setLikes(getByPostId(post.getId()));
        }
    }
}
