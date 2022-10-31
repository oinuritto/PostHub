package ru.itis.kpfu.utils;

import org.apache.commons.codec.digest.DigestUtils;
import ru.itis.kpfu.models.User;

public class UserPasswordHasher {
    public static void HashUserPassword(User user) {
        String newPassword = getHashedPassword(user.getPassword());
        user.setPassword(newPassword);
    }

    public static String getHashedPassword(String password) {
        String someSalt = "%890%$";
        return DigestUtils.md5Hex(DigestUtils.md5Hex(password + someSalt) + someSalt);
    }
}
