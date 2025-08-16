package org.example.config;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordConfig {


    //Hash a password for the 1st  time
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    //check that a plain text password matches previous one hashed
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

}
