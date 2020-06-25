package com.mnzit.learner.spring.spel.demospel;

import lombok.Getter;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Getter
public class TestMethod {

    public String name = null;

    public static Integer printSquare(Integer number) {
        return (number * number);
    }

    public Integer cube(Integer number) {
        return (number * number * number);
    }
}
