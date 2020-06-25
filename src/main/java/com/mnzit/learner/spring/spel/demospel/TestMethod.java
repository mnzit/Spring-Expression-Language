package com.mnzit.learner.spring.spel.demospel;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Getter
@Setter
public class TestMethod {

    private String name = null;

    public static Integer printSquare(Integer number) {
        return (number * number);
    }

    public Integer cube(Integer number) {
        return (number * number * number);
    }

    public static String reverseString(String input) {
        StringBuilder backwards = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            backwards.append(input.charAt(input.length() - 1 - i));
        }
        return backwards.toString();
    }
}
