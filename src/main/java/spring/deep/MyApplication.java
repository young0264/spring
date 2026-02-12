package spring.deep;

import org.springframework.beans.BeansException;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.servlet.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyApplication {

    public static void run(Class<?> applicationClass, String... args) {
        System.out.println("======= Hello DeepApplication =======");
        // DispatcherServlet이 사용할 ApplicationContext
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() throws BeansException {
                super.onRefresh();
                // Add Servlet to servlet-container
                // 웹프로그래밍은 요청을 받아서 응답을 만들어 내는 것.
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                //Application context 주입.

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet
                    ).addMapping("/*"); //servlet container에 매핑정보 등록
                });
                webServer.start();
            }
        };

        applicationContext.register(applicationClass);
        applicationContext.refresh(); // applicationContext 초기화~템플릿메서드(bean object 생성됨)
    }
}
