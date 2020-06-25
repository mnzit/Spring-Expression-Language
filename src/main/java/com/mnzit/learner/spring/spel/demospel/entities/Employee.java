package com.mnzit.learner.spring.spel.demospel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@AllArgsConstructor
public class Employee {
    public String name;
    public int telNo;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getTelNo() {
        return telNo;
    }


    public void setTelNo(int telNo) {
        this.telNo = telNo;
    }

}
