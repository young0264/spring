package spring.deep;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import spring.deep.controller.HelloController;

import java.io.IOException;

@SpringBootApplication
public class DeepApplication {

	public static void main(String[] args) {
		System.out.println("======= Hello DeepApplication =======");
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);
		applicationContext.refresh(); // applicationContext 초기화(bean object 생성됨)

		// Add Servlet to servlet-container
		// 익명클래스 활용
		// 웹프로그래밍은 요청을 받아서 응답을 만들어 내는 것.
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			HelloController helloController = new HelloController();

			servletContext.addServlet("frontController", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						String name = req.getParameter("name");

						HelloController helloController = applicationContext.getBean(HelloController.class);
						String ret = helloController.hello(name);

						// (1)status code, (2)content-type, (2)body
//						resp.setStatus(HttpStatus.OK.value());
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println("ret: " + ret);
					} else if (req.getRequestURI().equals("/user")) {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					} else {
					}
				}
			}).addMapping("/*"); //servlet container에 매핑정보 등록
        });
		webServer.start();
	}

}
