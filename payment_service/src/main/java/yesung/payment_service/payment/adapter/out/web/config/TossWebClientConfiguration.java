package yesung.payment_service.payment.adapter.out.web.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class TossWebClientConfiguration {

	@Value("${PSP.toss.url}")
	private String baseUrl;
	@Value("${PSP.toss.secretKey}")
	private String secretKey;

	@Bean
	public WebClient tossPaymentWebClient() {
		final String encodedSecretKey = Base64.getEncoder().encodeToString((secretKey + ":").getBytes());
		return WebClient.builder()
			.baseUrl(baseUrl)
			.defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedSecretKey)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
			.clientConnector(reactorClientConnector())
			.codecs(ClientCodecConfigurer::defaultCodecs)
			.build();
	}

	private ClientHttpConnector reactorClientConnector() {
		return new ReactorClientHttpConnector(HttpClient.create(
			ConnectionProvider.builder("toss-payment").build()
		));
	}
}
