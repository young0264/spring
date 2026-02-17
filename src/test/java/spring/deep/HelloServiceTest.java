package spring.deep;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.deep.controller.HelloController;
import spring.deep.service.HelloDecorator;
import spring.deep.service.SimpleHelloService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@UnitTest
@interface FastUnitTest {
}

@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD }) // @Test 내 확인
@Retention(RetentionPolicy.RUNTIME)
@Test
@interface UnitTest {
}

public class HelloServiceTest {
    @UnitTest
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
