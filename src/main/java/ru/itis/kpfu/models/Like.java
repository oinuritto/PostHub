package ru.itis.kpfu.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
//@ToString
public class Like {
    private Long id;
    private Long userId;
    private Long postId;
}
