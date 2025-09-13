package service;

public class ValidationService {
    public static void validateRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " должен быть от " + min + " до " + max);
        }
    }
}