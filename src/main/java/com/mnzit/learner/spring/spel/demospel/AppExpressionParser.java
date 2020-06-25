package com.mnzit.learner.spring.spel.demospel;

import com.mnzit.learner.spring.spel.demospel.entities.Company;
import com.mnzit.learner.spring.spel.demospel.entities.Employee;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 * @reference: https://docs.spring.io/spring/docs/3.0.x/reference/expressions.html
 */
public class AppExpressionParser {
    public static void main(String[] args) throws NoSuchMethodException {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test10();
        test11();
        test12();
        test13();
        test14();
        test15();
        test16();
        test17();
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

        List numbers = (List) parser.parseExpression("{1,2,3,4}").getValue(List.class);

        numbers.forEach(System.out::println);

        List listOfLists = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue(List.class);

        listOfLists.forEach(System.out::println);
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
        List<Employee> list = parser.parseExpression("employees.?[name == 'Manjit']").getValue(sec1, List.class);

        System.out.println("All Matched=======================");
        for (Employee emp : list) {
            System.out.println("Name of employee : " + emp.getName());
            System.out.println("Employee telephone num : " + emp.getTelNo());
        }
    }

    public static void test4() {
        Employee employee = new Employee();

        //Passing the object of Employee class to StandardEvaluationContext, which is going to evaluate the expressions in the context of this object.
        StandardEvaluationContext stContext = new StandardEvaluationContext(employee);

        //Creating an object of SpelExpressionParser class, used to parse the SpEL expression
        SpelExpressionParser parser = new SpelExpressionParser();

        //Calling parseRaw() method of SpelExpressionParser, which parses the expression and returns an SpelEpression object
        SpelExpression expression = parser.parseRaw("name");

        //Calling setValue() method sets the value of "name" with the value "Manjit" in the context of object of Employee.
        expression.setValue(stContext, "Manjit");

        System.out.println("EmployeeName : " + employee.getName());
    }

    public static void test5() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'put spel expression here'");
        String msg = exp.getValue(String.class);

        System.out.println(msg);
    }

    public static void test6() {
        ExpressionParser parser = new SpelExpressionParser();
        Employee employee = new Employee("Manjit", 100);
        StandardEvaluationContext itemContext = new StandardEvaluationContext(employee);

        //display the value of item.name property
        Expression exp = parser.parseExpression("name");

//        exp.setValue(itemContext, "Ramesh");

        String msg = exp.getValue(itemContext, String.class);

        System.out.println(msg);
    }

    public static void test7() {
        ExpressionParser parser = new SpelExpressionParser();

        //literal expressions
        Expression exp = parser.parseExpression("'Hello World'");
        String msg1 = exp.getValue(String.class);
        System.out.println(msg1);

        //method invocation
        Expression exp2 = parser.parseExpression("'Hello World'.length()");
        int msg2 = (Integer) exp2.getValue();
        System.out.println(msg2);

        //Mathematical operators
        Expression exp3 = parser.parseExpression("100 * 2");
        int msg3 = (Integer) exp3.getValue();
        System.out.println(msg3);

        //create an item object
        Employee employee = new Employee("Manjit", 100);
        //test EL with item object
        StandardEvaluationContext itemContext = new StandardEvaluationContext(employee);

        //test if item.name == 'Manjit'
        Expression exp5 = parser.parseExpression("name == 'Manjit'");
        boolean msg5 = exp5.getValue(itemContext, Boolean.class);
        System.out.println(msg5);
    }

    public static void test8() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("T(com.mnzit.learner.spring.spel.demospel.TestMethod).printSquare(16)");
        Integer result = exp.getValue(Integer.class);
        System.out.println(result);
    }

    public static void test9() {
        // create an array of integers
        List<Integer> primes = new ArrayList<Integer>();
        primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));

        // create parser and set variable 'primes' as the array of integers
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("primes", primes);

        /**
         * #primes refers to the primes list.
         *
         * The ?[] selection operator matches every object i in the primes list which is not null and which matches the criteria,
         * given in the brackets. In our example, the criteria is #this > 10. #this refers to the current evaluation object, which
         * in our example would be the object from the list that is being checked at the moment for not-null and > 10.
         */

        Expression expression = parser.parseExpression("#primes.?[#this>10]");
        List<Integer> primesGreaterThanTen = (List<Integer>) expression.getValue(ctx);

        for (Integer i : primesGreaterThanTen) { // [11, 13, 17]
            System.out.println(i);
        }
    }

    public static void test10() {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        TestMethod testMethod = new TestMethod();
        ctx.setRootObject(testMethod);

        Integer number = 16;
        ctx.setVariable("value", number);
        String statement = "#root.cube(#value)";
        Expression expression = parser.parseExpression(statement);

        Integer result = expression.getValue(ctx, Integer.class);

        System.out.println(result);
    }

    public static void test11() {
        TestMethod testMethod = new TestMethod();
        StandardEvaluationContext ctx = new StandardEvaluationContext(testMethod);

        ExpressionParser parser = new SpelExpressionParser();

        Expression expression = parser.parseExpression("cube(16)");

        Integer result = expression.getValue(ctx, Integer.class);

        System.out.println(result);
    }

    public static void test12() {
        TestMethod testMethod = new TestMethod();
        StandardEvaluationContext ctx = new StandardEvaluationContext(testMethod);

        ExpressionParser parser = new SpelExpressionParser();

        parser.parseExpression("Name").setValue(ctx, "Manjit");
        // Works based on getter and setter
        parser.parseExpression("Name = 'Mnzit'").getValue(ctx, String.class);

        System.out.println(testMethod.getName());

        Employee employee = new Employee("Manjit", 1000);
        StandardEvaluationContext context = new StandardEvaluationContext(employee);
        context.setVariable("newName", "Mnzit");

        parser.parseExpression("Name = #newName").getValue(context);

        System.out.println(employee.getName()); // "Mnzit"

    }

    public static void test13() {
        ExpressionParser parser = new SpelExpressionParser();
        String randomPhrase =
                parser.parseExpression("Random number: #{T(java.lang.Math).random()}",
                        new TemplateParserContext()).getValue(String.class);
        System.out.println(randomPhrase);

        // custom template
        randomPhrase =
                parser.parseExpression("Random number: {{T(java.lang.Math).random()}}",
                        new TemplateParserContext("{{", "}}")).getValue(String.class);
        System.out.println(randomPhrase);
    }

    public static void test14() {
        ExpressionParser parser = new SpelExpressionParser();

        Class dateClass = parser.parseExpression("T(java.util.Date)").getValue(Class.class);
        System.out.println(dateClass);

        Class stringClass = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println(stringClass);
    }

    public static void test15() {
        ExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("newName", "Mnzit");
        context.setVariable("isNull", null);

        // Elvis Operator
        Object name = parser.parseExpression("#newName.equals('Mnzit')?:'Not Known'").getValue(context);

        System.out.println(name);  // 'Not Known'

        String booleanString = parser.parseExpression("false ? 'isTrue' : 'isFalse'").getValue(String.class);

        System.out.println(booleanString);

        // Safe Navigation operator
        String length = parser.parseExpression("#isNull?.length()").getValue(String.class);

        System.out.println(length);  // null - does not throw NullPointerException!!!
    }

    public static void test16() throws NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        context.registerFunction("reverseString",
                TestMethod.class.getDeclaredMethod("reverseString",
                        new Class[]{String.class}));

        String helloWorldReversed = parser.parseExpression("#reverseString('hello')").getValue(context, String.class);

        System.out.println(helloWorldReversed);
    }

    // copied
    public static void test17() {
        ExpressionParser parser = new SpelExpressionParser();
        int[] numbers1 = (int[]) parser.parseExpression("new int[4]").getValue();

        // Array with initializer
        int[] numbers2 = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue();

        // Multi dimensional array
        int[][] numbers3 = (int[][]) parser.parseExpression("new int[4][5]").getValue();

        System.out.println(numbers1.length);
        System.out.println(numbers2.length);
        System.out.println(numbers3.length);

        TestMethod testMethod = parser.parseExpression("new com.mnzit.learner.spring.spel.demospel.TestMethod()").getValue(TestMethod.class);

        System.out.println(testMethod.getName());
    }

    // copied
    public static void test18() {

        ExpressionParser parser = new SpelExpressionParser();

        // Addition
        int two = parser.parseExpression("1 + 1").getValue(Integer.class); // 2

        String testString = parser.parseExpression("'test' + ' ' + 'string'").getValue(String.class);  // 'test string'

        // Subtraction
        int four = parser.parseExpression("1 - -3").getValue(Integer.class); // 4

        double d = parser.parseExpression("1000.00 - 1e4").getValue(Double.class); // -9000

        // Multiplication
        int six = parser.parseExpression("-2 * -3").getValue(Integer.class); // 6

        double twentyFour = parser.parseExpression("2.0 * 3e0 * 4").getValue(Double.class); // 24.0

        // Division
        int minusTwo = parser.parseExpression("6 / -3").getValue(Integer.class); // -2

        double one = parser.parseExpression("8.0 / 4e0 / 2").getValue(Double.class); // 1.0

        // Modulus
        int three = parser.parseExpression("7 % 4").getValue(Integer.class); // 3

        int one1 = parser.parseExpression("8 / 5 % 2").getValue(Integer.class); // 1

        // Operator precedence
        int minusTwentyOne = parser.parseExpression("1+2-3*8").getValue(Integer.class); // -21

        // -- AND --
        // evaluates to false
        boolean falseValue = parser.parseExpression("true and false").getValue(Boolean.class);

        // -- OR --
        // evaluates to true
        boolean trueValue1 = parser.parseExpression("true or false").getValue(Boolean.class);

        // -- NOT --
        // evaluates to false
        boolean falseValue1 = parser.parseExpression("!true").getValue(Boolean.class);

    }
}

