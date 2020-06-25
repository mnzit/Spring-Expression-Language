package com.mnzit.learner.spring.spel.demospel;

import com.mnzit.learner.spring.spel.demospel.entities.Company;
import com.mnzit.learner.spring.spel.demospel.entities.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
public class AppExpressionParser {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    public static void test1() {
        EvaluationContext sec1 = new StandardEvaluationContext();
        sec1.setVariable("name", "Manjit");
        sec1.setVariable("age", 22);

        ExpressionParser parser = new SpelExpressionParser();

        Expression expression = parser.parseExpression("#name.concat(#age)");

        String result = expression.getValue(sec1, String.class);

        System.out.println(result);
    }

    public static void test2() {
        ExpressionParser parser = new SpelExpressionParser();

        Expression expression = parser.parseExpression("{name: 'roofus', animal: 'dog', lifeSpan: 12}");

        Map result = expression.getValue(Map.class);

        result.forEach((k, v) -> {
            System.out.println("Key: " + k + ", Value: " + v);
        });
    }

    public static void test3() {

        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee("Manjit", 12345);
        employees.add(employee);
        employee = new Employee("Manjit", 12349);
        employees.add(employee);
        employee = new Employee("Ranjit", 54321);
        employees.add(employee);

        Company company = new Company("Bajra", employees);

        EvaluationContext sec1 = new StandardEvaluationContext(company);

        SpelExpressionParser parser = new SpelExpressionParser();

        /**
         * You can use the .^[] for selecting the first match
         */
        Employee resultEmployee = parser.parseRaw("employees.^[name == 'Manjit']").getValue(sec1, Employee.class);

        System.out.println("First Matched=======================");
        System.out.println("Name of employee : " + resultEmployee.getName());
        System.out.println("Employee telephone num : " + resultEmployee.getTelNo());


        /**
         * .$[] operator to select the last match items from collection respectively.
         */
        resultEmployee = parser.parseRaw("employees.$[name == 'Manjit']").getValue(sec1, Employee.class);

        System.out.println("Last Matched=======================");
        System.out.println("Name of employee : " + resultEmployee.getName());
        System.out.println("Employee telephone num : " + resultEmployee.getTelNo());


        /**
         * the filter operator which can be typed like .?[] Where you can define the filter criteria inside the braces.
         */
        List<Employee> list = parser.parseRaw("employees.?[name == 'Manjit']").getValue(sec1, List.class);

        System.out.println("All Matched=======================");
        for (Employee emp : list) {
            System.out.println("Name of employee : " + emp.getName());
            System.out.println("Employee telephone num : " + emp.getTelNo());
        }
    }
}
