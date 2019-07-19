package com.hb56.security.util;

import java.util.Random;


/**
 * @author Been
 */
public class RandomCode {

    public static String createSmsCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
