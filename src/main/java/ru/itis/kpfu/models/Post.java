package ru.itis.kpfu.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class Post {
    private Long id;
    private String text;
    private Long imgId;
    private Long userId;
    private List<Comment> comments;
}
