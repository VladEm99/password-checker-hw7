package org.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {

    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialCharsEng() {
        assertTrue( PasswordChecker.checkPasswordComplexity("Password123!") );
    }
    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialCharsRus() {
        assertTrue( PasswordChecker.checkPasswordComplexity("Пароль1234!") );
    }
    @Test
    void shouldReturnFalseForNotEnoughComplexPasswordWithMissingNumber() {
        assertFalse( PasswordChecker.checkPasswordComplexity("Password@@@") );
    }
    @Test
    void shouldReturnFalseForNotEnoughComplexPasswordWithMissingSpecialChar() {
        assertFalse( PasswordChecker.checkPasswordComplexity("Password2213") );
    }
    @Test
    void shouldReturnFalseForNotEnoughComplexPasswordNotEnoughLong() {
        assertFalse( PasswordChecker.checkPasswordComplexity("P2@") );
    }
    @Test
    void shouldReturnFalsePasswordInputIsEmpty() {
        assertFalse( PasswordChecker.checkPasswordComplexity("") );
    }
    @Test
    void shouldReturnTrueForComplexPasswordWithOnlyDigitsAndSpecialChars() {
        assertTrue( PasswordChecker.checkPasswordComplexity("123123@@@@") );
    }
    @Test
    void shouldReturnTrueForComplexPasswordWithSpacesNumbersSpecialChars() {
        assertTrue( PasswordChecker.checkPasswordComplexity("pa ss word123@") );
    }
    @Test
    void shouldReturnTrueForComplexPasswordWithOnlyUpperEngRegisterCharsNumbersSpecialChars() {
        assertTrue( PasswordChecker.checkPasswordComplexity("PASSWORDDD123@") );
    }
    @Test
    void shouldReturnTrueForComplexPasswordWithOnlyUpperRusRegisterCharsNumbersSpecialChars() {
        assertTrue( PasswordChecker.checkPasswordComplexity("КОНСОЛИДАЦИЯ123@") );
    }
}