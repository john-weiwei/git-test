package com.example.springproject.demo;

import com.example.springproject.demo.entity.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {

        int a = 0;
        System.out.println("运行test");
    }

    @Test
    void test() {
        String str = null;
        AtomicInteger atomicInteger = new AtomicInteger();
        Greeting greeting = new Greeting(atomicInteger.incrementAndGet(),"hi");
        System.out.println(greeting.toString());
        greeting.setShow(true);
        System.out.println(greeting.toString());

    }

    @Test
    void test2() {
        List<String> arrStr1 = new ArrayList<>();
        arrStr1.add("aa");
        arrStr1.add("ab");
        arrStr1.add("ac");
        arrStr1.add("ad");
        arrStr1.add("ae");
        List<String> arrStr2 = new ArrayList<>();
        arrStr2.add("aa");
        arrStr2.add("adv");
        arrStr2.add("ac");
        arrStr2.add("are");
        arrStr2.add("ae");
        for (String str: arrStr1) {
            Iterator<String> iterator = arrStr2.iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (str.equals(item)) {
                    iterator.remove();
                    break;
                }
            }
        }

        for (String str: arrStr2) {
            System.out.println(str);
        }
    }

    @Test
    void test3() {
        String str = " ";
        String regex = "\\s*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        System.out.println(b);
    }

    @Test
    void test4() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        String value = (String) parser.parseExpression("'Hello'").getValue();
        System.out.println(value);
    }


}
