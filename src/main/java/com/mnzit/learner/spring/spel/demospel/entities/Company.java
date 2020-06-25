package com.mnzit.learner.spring.spel.demospel.entities;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@AllArgsConstructor
public class Company {
    public String name;
    public List<Employee> employees;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Employee> getEmployees() {
        return employees;
    }


    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
