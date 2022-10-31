package ru.itis.kpfu.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class Comment {
    private Long id;
    private String text;
    private Long userId;
    private Long postId;
}
