package spring.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

    // simulate proxy in spring container
    @Test
    void useContainerWithConifiguration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        // @Configuration 사용시 같은 Object 반환함
        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    // simulate spring container in Java
    @Test
    void conifiguration() {
        Common common = new Common();
        MyConfig myConfig = new MyConfig();
        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();

        Assertions.assertThat(new Common()).isNotSameAs(new Common());
        Assertions.assertThat(common).isSameAs(common);
        Assertions.assertThat(bean1).isNotSameAs(bean2);
    }

    // simulate proxy in Java code
    @Test
    void proxyCommonMethod() {
        MyConfigProxy myConfigProxy = new MyConfigProxy();
        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    // proxy 생성 (캐싱/재사용 형태)
    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        Common common() {
            if (common == null) {
                this.common = super.common();
            }
            return this.common;
        }
    }

    // Bean1 <-- Object
    // Bean2 <-- Object
    @Configuration(proxyBeanMethods = true)
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

   static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {

    }


}
