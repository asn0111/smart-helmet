package com.asn.project;

import java.util.Random;

public class CodeGenerator {
    public static String generateCode() {
        Random random = new Random();
        int code = 10000 + random.nextInt(90000); // Ensures a 5-digit code
        return String.valueOf(code);
    }
}
