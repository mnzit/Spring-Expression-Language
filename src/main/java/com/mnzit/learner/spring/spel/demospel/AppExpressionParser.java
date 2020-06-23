package com.mnzit.learner.spring.spel.demospel;

import com.mnzit.learner.spring.spel.demospel.data.Customer;
import com.mnzit.learner.spring.spel.demospel.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
public class AppExpressionParser {
    private static List<String> names;

    @Autowired
    private static ApplicationContext context;

    //-Duser.language=ms -Duser.country=MY -Duser.timezone=Asia/Kuala_lumpur

    static {
        names = Arrays.asList("Manjit", "Mohan", "Aadarsh", "Biraj");
    }
    @Value("#{names}")
    private static List<String> nameList = new ArrayList<>();

    @Value("#{'John Doe'}")
    public String name;


    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression exp1 = parser.parseExpression("'Hello World'");
        String message = (String) exp1.getValue();
        System.out.println(message);

        Expression exp2 = parser.parseExpression("'Hello World'.length()");
        System.out.println(exp2.getValue());

        Expression exp3 = parser.parseExpression("'Hello World'.length() * 10");
        System.out.println(exp3.getValue());

        Expression exp4 = parser.parseExpression("'Hello World'.length() > 10");
        System.out.println(exp4.getValue());

        Expression exp5 = parser.parseExpression("'Hello World'.length() > 10 and 'Hello World'.length() == 11");
        System.out.println(exp5.getValue());

        EvaluationContext ec1 = new StandardEvaluationContext();

        ec1.setVariable("greeting", "Hello UK");

        String msg1 = (String) parser.parseExpression("#greeting.substring(6)").getValue(ec1);

        System.out.println(msg1);

        System.out.println("#####################################");

        User user = new User();
        EvaluationContext userContext = new StandardEvaluationContext(user);

        parser.parseExpression("country").setValue(userContext, "USA");
        System.out.println(user.getCountry());

        parser.parseExpression("language").setValue(userContext, "en");
        System.out.println(user.getLanguage());

        parser.parseExpression("timeZone").setValue(userContext, "America/New_York");
        System.out.println(user.getTimeZone());


        EvaluationContext propsContext = new StandardEvaluationContext();
        propsContext.setVariable("systemProperties", System.getProperties());

        Expression expCountry = parser.parseExpression("#systemProperties['user.country']");
        parser.parseExpression("country").setValue(userContext, expCountry.getValue(propsContext));
        System.out.println(user.getCountry());

        Expression expLanguage = parser.parseExpression("#systemProperties['user.language']");
        parser.parseExpression("language").setValue(userContext, expLanguage.getValue(propsContext));
        System.out.println(user.getLanguage());

        Expression expTimeZone = parser.parseExpression("#systemProperties['user.timezone']");
        parser.parseExpression("timeZone").setValue(userContext, expTimeZone.getValue(propsContext));
        System.out.println(user.getTimeZone());

        Customer obj = (Customer) context.getBean("customerBean");
        System.out.println(obj);
    }
}
