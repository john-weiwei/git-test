package com.example.springproject.demo.controller;

import com.example.springproject.demo.entity.Greeting;
import com.example.springproject.demo.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangWeiWei
 * @date 2020/9/17 18:22
 * @description
 */
@RestController
public class GreetingController {
    private static final String temp = "Hello,%s";
    private final AtomicInteger count = new AtomicInteger();

    @Autowired
    private IGreetingService greetingService;

//    @ModelAttribute
//    public Greeting setGreeting() {
//        return new Greeting(1,"Hi");
//    }

    /**
     * @RequestParam  声明的方法参数和web参数进行绑定
     * 加了此注解的参数默认为必填，可通过设置required为false，改为选填
     * @param name
     * @return
     */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "World",required = false) String name) {
        return new Greeting(count.incrementAndGet(),String.format(temp,name));
    }

    /**
     * @PathVariable  捕获访问URI携带的参数值
     * URI参数值会被自动转换为对应的数据类型
     * @PathVariable("id") 可以明确指定URI的参数名称
     *
     * @RequestHeader 在控制器方法参数绑定请求头
     * @param id
     * @return
     */
    @GetMapping("/queryGreeting/{id}")
    public Greeting helloGreeting(@PathVariable("id") int id,
                                  @RequestHeader("Accept-Encoding") String encoding) {
        return null;
    }

    /**
     * @CookieValue  在控制器方法参数中绑定http的cookies值，参数类型不是string的话，也会应用自动转型
     * @param id
     * @param cookie
     * @return
     */
    @GetMapping("/queryGreetings/{id}")
    public Greeting helloGreetings(@PathVariable("id") int id,
                                  @CookieValue("JSESSIONID") String cookie) {
        return null;
    }

    /**
     * @ModelAttribute 在方法属性中使用此注解来访问模型中的属性，
     * 如果是不存在的情况下将其实例化，这个模型属性也会覆盖从http servlet请求中匹配了字段名称。
     * 想访问没有绑定的数据，把binding设置为false，也可以在controller中注入一个Model
     * @ModelAttribute
     *     public Greeting setGreeting() {
     *         return new Greeting(1,"Hi");
     *     }
     * @param greeting
     * @param result 存放错误信息
     * @return
     */
    @PostMapping("/greeting/{id}/edit")
    public String processGreeting(@Valid @ModelAttribute(binding = false,value = "greeting") Greeting greeting,
                                  BindResult result) {
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    @ModelAttribute
    public String processingGreetingHi(@RequestParam int id) {
        return null;
    }

    @GetMapping("/greeting/sayHelloToYou")
    public String sayHelloToYou(@RequestParam("msg") String msg,@RequestParam("name") String name) {
        System.out.println(msg);
        System.out.println(name);
        greetingService.sayHello(msg,name);
        return "Ok";
    }
}







