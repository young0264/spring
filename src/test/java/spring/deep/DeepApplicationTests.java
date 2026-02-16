package spring.deep;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

class DeepApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void helloApi() {
		// RestClient를 사용한 테스트 (Spring 6.1+)
		RestClient restClient = RestClient.create();

		ResponseEntity<String> response = restClient.get()
			.uri("http://localhost:8080/hello?name=spring")
			.retrieve()
			.toEntity(String.class);

		// 1. status code 200 검증
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		// 2. header: text/plain 검증
		Assertions.assertThat(response.getHeaders().getContentType().toString())
			.contains(MediaType.TEXT_PLAIN_VALUE);

		// 3. body: hello spring 검증
		Assertions.assertThat(response.getBody()).isNotNull();
		Assertions.assertThat(response.getBody()).contains("Hello");
		Assertions.assertThat(response.getBody()).contains("spring");
	}
	@Test
	void failHelloApi() {
		// RestClient를 사용한 테스트 (Spring 6.1+)
		RestClient restClient = RestClient.create();


		Assertions.assertThatThrownBy(() -> {
			restClient.get()
					.uri("http://localhost:8080/hello?name=")
					.retrieve()
					.toEntity(String.class);
		}).isInstanceOf(HttpServerErrorException.InternalServerError.class);

		// 1. status code 500 실패 검증
//		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
//
//		// 2. header: text/plain 검증
//		Assertions.assertThat(response.getHeaders().getContentType().toString())
//			.contains(MediaType.TEXT_PLAIN_VALUE);

		// 3. body: hello spring 검증
//		Assertions.assertThat(response.getBody()).isNotNull();
//		Assertions.assertThat(response.getBody()).contains("Hello");
//		Assertions.assertThat(response.getBody()).contains("spring");
	}
}
