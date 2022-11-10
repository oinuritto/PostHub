package ru.itis.kpfu.services.impl;

import ru.itis.kpfu.models.User;
import ru.itis.kpfu.repositories.UsersRepository;
import ru.itis.kpfu.services.PostsService;
import ru.itis.kpfu.services.UsersService;
import ru.itis.kpfu.utils.UserPasswordHasher;
import ru.itis.kpfu.validation.UserValidator;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PostsService postsService;
    private final UserValidator userValidator;

    public UsersServiceImpl(UsersRepository usersRepository, PostsService postsService) {
        this.usersRepository = usersRepository;
        this.postsService = postsService;
        this.userValidator = new UserValidator();
    }

    @Override
    public void signUp(User user) {
        // throws IllegalArgumentException if user data is invalid
        userValidator.validateUser(user);
//        UserPasswordHasher.HashUserPassword(user);
        user.setPassword(UserPasswordHasher.getHashedPassword(user.getPassword()));
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
    public void updateRatingByPostId(Long id, int rateDiff) {
        User user = getRegisteredUserById(postsService.getPostById(id).getUserId());
        user.setRating(user.getRating() + rateDiff);
        update(user);
    }

    @Override
    public void update(User user, boolean mustHashPassword) throws IllegalArgumentException {
        if (mustHashPassword) {
            userValidator.validateUser(user);
//            UserPasswordHasher.HashUserPassword(user);
            user.setPassword(UserPasswordHasher.getHashedPassword(user.getPassword()));
        }
        usersRepository.update(user);
    }

    @Override
    public void update(User user) throws IllegalArgumentException {
        // if password not changing and hashed
        // т.к. длина хэша для пароля не подходит,
        // то берем просто копию юзера с подходящим паролем для валидации
        User userCopy = User.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password("somePassword")
                .build();
        userValidator.validateUser(userCopy);
        usersRepository.update(user);
    }

    @Override
    public User getRegisteredUserByUsername(String username) {
        return usersRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("This username doesn't exist"));
    }

    @Override
    public User getRegisteredUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("This id doesn't exist"));
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getAllUsers(boolean sortedByRatingDesc) {
        if (sortedByRatingDesc) {
            return usersRepository.findAllOrderByRatingDesc();
        }
        return getAllUsers();
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
