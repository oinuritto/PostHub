package ru.itis.kpfu.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Post {
    private Long id;
    private String title;
    private String text;
    private Long imgId;
    private Long userId;
    private List<Like> likes;
}
