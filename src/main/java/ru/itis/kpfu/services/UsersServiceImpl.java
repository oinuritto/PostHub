package ru.itis.kpfu.services;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.repositories.UsersRepository;
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
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean isRegisteredUser(User user) {
        return usersRepository.findUserByUsername(user.getUsername()).isPresent();
    }

    @Override
    public User getRegisteredUserByUsername(String username) {
        return usersRepository.findUserByUsername(username)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }
}
