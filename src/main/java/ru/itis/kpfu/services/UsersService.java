package ru.itis.kpfu.services;

import ru.itis.kpfu.models.User;

import java.util.List;

public interface UsersService {
    void signUp(User user);

    User getRegisteredUserByUsernamePassword(String username, String password) throws IllegalArgumentException;

    boolean isRegisteredUser(User user);

    User getRegisteredUserByUsername(String username);

    List<User> getAllUsers();
}
