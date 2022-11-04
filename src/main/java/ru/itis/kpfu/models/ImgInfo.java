package ru.itis.kpfu.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImgInfo {
    private Long id;
    private Long size;
    private String type;
    private String originalFileName;
    private String storageFileName;

}