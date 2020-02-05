package br.common;

import java.security.SecureRandom;
import java.util.Random;

public class RandomString {
    private final static Random RANDOM = new SecureRandom();
    private final static String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generate(int length) {
        StringBuilder carryString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            carryString.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(carryString);
    }

}
