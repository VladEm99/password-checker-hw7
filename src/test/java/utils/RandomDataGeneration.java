package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGeneration {
    public static String generateName(){
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedCustomerName = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedCustomerName;
    }
}
