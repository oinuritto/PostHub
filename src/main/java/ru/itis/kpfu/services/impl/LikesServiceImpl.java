package ru.itis.kpfu.services.impl;

import ru.itis.kpfu.models.Like;
import ru.itis.kpfu.repositories.LikesRepository;
import ru.itis.kpfu.repositories.UsersRepository;
import ru.itis.kpfu.services.LikesService;
import ru.itis.kpfu.validation.UserValidator;

import java.util.List;

public class LikesServiceImpl implements LikesService {
    private final LikesRepository likesRepository;

    public LikesServiceImpl(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    @Override
    public void addLike(Like like) {
        likesRepository.save(like);
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
    }
}
