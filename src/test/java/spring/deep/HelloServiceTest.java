package spring.deep;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.deep.controller.HelloController;
import spring.deep.service.HelloDecorator;
import spring.deep.service.SimpleHelloService;

public class HelloServiceTest {
    @Test
    void simpleServiceTest() {
        SimpleHelloService helloService = new SimpleHelloService();
        String ret = helloService.sayHello("Test");
        Assertions.assertThat(ret).isEqualTo("Hello " + "Test" + " from SimpleHelloService");
    }

    @Test
    void decoratorTest() {
        HelloDecorator helloDecorator = new HelloDecorator(service -> service);
        helloDecorator.sayHello("Test");
        Assertions.assertThat(helloDecorator.sayHello("Test")).isEqualTo("===Test===");
    }
}
