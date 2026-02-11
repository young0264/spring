package spring.deep.controller;

// Simple java class without any Spring
public class HelloController {
    public String hello(String name) {
        System.out.println("======= Hello GetMapping =======");
        return "Hello " + name;
    }
}