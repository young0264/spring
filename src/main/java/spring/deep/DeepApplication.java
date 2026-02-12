package spring.deep;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.servlet.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import spring.deep.controller.HelloController;
import spring.deep.service.SimpleHelloService;

import java.io.IOException;

//@SpringBootApplication
public class DeepApplication {

	public static void main(String[] args) {
		System.out.println("======= Hello DeepApplication =======");
		// DispatcherServlet이 사용할 ApplicationContext
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
			@Override
			protected void onRefresh() throws BeansException {
				super.onRefresh();
				// Add Servlet to servlet-container
				// 웹프로그래밍은 요청을 받아서 응답을 만들어 내는 것.
				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this)
					).addMapping("/*"); //servlet container에 매핑정보 등록
				});
				webServer.start();
			}
		};

		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh(); // applicationContext 초기화~템플릿메서드(bean object 생성됨)
	}

}
