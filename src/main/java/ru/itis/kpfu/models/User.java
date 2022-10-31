package ru.itis.kpfu.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
//@ToString
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
