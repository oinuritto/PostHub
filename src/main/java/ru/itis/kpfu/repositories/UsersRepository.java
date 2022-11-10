package ru.itis.kpfu.repositories;

import ru.itis.kpfu.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    List<User> findAll();

    List<User> findAllOrderByRatingDesc();

    void save(User user);

    Optional<User> findById(Long id);

    void update(User user);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByUsernamePassword(String username, String password);

    List<User> findAllLikeUsername(String username);

    void delete(Long id);
}
