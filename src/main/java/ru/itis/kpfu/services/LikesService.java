package ru.itis.kpfu.services;

import ru.itis.kpfu.models.Like;
import ru.itis.kpfu.models.Post;

import java.util.List;

public interface LikesService {
    void addLike(Like like);

    List<Like> getByPostId(Long id);

    boolean isExist(Like like);

    void removeLike(Like like);

    void setLikesToPosts(List<Post> posts);
}
