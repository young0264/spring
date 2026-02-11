package spring.deep;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import java.io.IOException;

@SpringBootApplication
public class DeepApplication {

	public static void main(String[] args) {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		// Add Servlet to servlet-container
		// 익명클래스 활용
		// 웹프로그래밍은 요청을 받아서 응답을 만들어 내는 것.
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// (1)status code, (2)content-type, (2)body
					resp.setStatus(200);
					resp.setHeader("Content-Type", "text/plain");
					resp.getWriter().println("Hello Created Servlet");
				}
			}).addMapping("/hello");
        });
		webServer.start();
	}

}
