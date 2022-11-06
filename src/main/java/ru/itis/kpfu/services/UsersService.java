package ru.itis.kpfu.services;

import ru.itis.kpfu.models.User;

import java.util.List;

public interface UsersService {
    void signUp(User user);

    User getRegisteredUserByUsernamePassword(String username, String password) throws IllegalArgumentException;

    boolean isRegisteredUser(User user);

    void update(User user, boolean mustHashPassword);

    void update(User user);

    User getRegisteredUserByUsername(String username);

    User getRegisteredUserById(Long id);

    List<User> getAllUsers();

    List<User> getAllUsersLikeUsername(String username);

    void deleteUser(User user);
}
