package ru.itis.kpfu.services.impl;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.repositories.UsersRepository;
import ru.itis.kpfu.services.UsersService;
import ru.itis.kpfu.utils.UserPasswordHasher;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void signUp(User user) {
        UserPasswordHasher.HashUserPassword(user);
        usersRepository.save(user);
    }

    @Override
    public User getRegisteredUserByUsernamePassword(String username, String password) throws IllegalArgumentException{
        password = UserPasswordHasher.getHashedPassword(password);
        return usersRepository.findUserByUsernamePassword(username, password)
                .orElseThrow(() -> new IllegalArgumentException("Wrong username or password"));
    }

    @Override
    public boolean isRegisteredUser(User user) {
        return usersRepository.findUserByUsername(user.getUsername()).isPresent();
    }

    @Override
    public void update(User user, boolean mustHashPassword) {
        if (mustHashPassword) {
            UserPasswordHasher.HashUserPassword(user);
        }
        usersRepository.update(user);
    }

    @Override
    public void update(User user) {
        usersRepository.update(user);
    }

    @Override
    public User getRegisteredUserByUsername(String username) {
        return usersRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("This username doesn't exist"));
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getAllUsersLikeUsername(String username) {
        return usersRepository.findAllLikeUsername(username);
    }

    @Override
    public void deleteUser(User user) {
        if (user.getId() != null) {
            usersRepository.delete(user.getId());
        }
    }
}
