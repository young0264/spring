package spring.deep.service;

public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name + " from SimpleHelloService";
    }
}
