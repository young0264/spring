package spring.deep.controller;

import spring.deep.service.SimpleHelloService;

import java.util.Objects;

// Simple java class without any Spring
public class HelloController {
    public String hello(String name) {
        SimpleHelloService helloService = new SimpleHelloService();
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}