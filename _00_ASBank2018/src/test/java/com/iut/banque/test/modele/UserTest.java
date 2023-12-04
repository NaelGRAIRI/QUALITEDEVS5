package com.iut.banque.test.modele;

import com.iut.banque.modele.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void setUserPwd_ShouldHashPassword() {
        // Arrange
        Utilisateur user = new Utilisateur();
        String plainTextPassword = "password123";

        // Act
        user.setUserPwd(plainTextPassword);

        // Assert
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        assertTrue(passwordEncoder.matches(plainTextPassword, user.getUserPwd()));
    }

    @Test
    public void checkPassword_ShouldReturnTrueForCorrectPassword() {
        // Arrange
        Utilisateur user = new Utilisateur();
        String plainTextPassword = "password123";
        user.setUserPwd(plainTextPassword);

        // Act
        boolean result = user.checkPassword(plainTextPassword);

        // Assert
        assertTrue(result);
    }

    @Test
    public void checkPassword_ShouldReturnFalseForIncorrectPassword() {
        // Arrange
        Utilisateur user = new Utilisateur();
        String storedPassword = "hashedPassword";
        user.setUserPwd(storedPassword);
        String incorrectPassword = "incorrectPassword";

        // Act
        boolean result = user.checkPassword(incorrectPassword);

        // Assert
        assertFalse(result);
    }
}
