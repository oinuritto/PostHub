package ru.itis.kpfu.validation;

import ru.itis.kpfu.models.Post;

public class PostValidator {
    public void validate(Post post) {
        if (post.getTitle() == null || post.getTitle().length() <= 3 || post.getTitle().length() >= 30) {
            throw new IllegalArgumentException("Post title length must be less than 30 and greater than 3.");
        }
        if (post.getText() == null || post.getText().length() <= 6 || post.getText().length() > 1600) {
            throw new IllegalArgumentException("Post text length must be less than or equal 1600 greater than 6.");
        }
    }
}
