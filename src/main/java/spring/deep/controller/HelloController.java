package spring.deep.controller;

import spring.deep.service.HelloService;

import java.util.Objects;

// Simple java class without any Spring
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}