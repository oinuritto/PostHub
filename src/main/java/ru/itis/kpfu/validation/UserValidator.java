package ru.itis.kpfu.validation;

import ru.itis.kpfu.models.User;

public class UserValidator {
    public void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().length() <= 3 || user.getUsername().length() >= 20) {
            throw new IllegalArgumentException("Username length must be less than 20 and greater than 3.");
        }
        if (user.getPassword() == null || user.getPassword().length() <= 6 || user.getPassword().length() >= 20) {
            throw new IllegalArgumentException("Password length must be less than 20 and greater than 6.");
        }
        if (user.getFirstName() == null || user.getFirstName().length() <= 2 || user.getFirstName().length() >= 20) {
            throw new IllegalArgumentException("First name length must be less than 20 and greater than 2.");
        }
        if (user.getLastName() == null || user.getLastName().length() <= 3 || user.getLastName().length() >= 20) {
            throw new IllegalArgumentException("Last name length must be less than 20 and greater than 3.");
        }

    }
}
