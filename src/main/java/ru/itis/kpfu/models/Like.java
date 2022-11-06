package ru.itis.kpfu.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Like {
    private Long userId;
    private Long postId;
}
