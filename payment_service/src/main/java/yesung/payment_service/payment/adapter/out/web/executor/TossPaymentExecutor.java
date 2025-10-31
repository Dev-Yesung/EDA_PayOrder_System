package yesung.payment_service.payment.adapter.out.web.executor;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class TossPaymentExecutor {

	private static final String uri = "/v1/payments/confirm";
	private final WebClient tossPaymentWebClient;

	public TossPaymentExecutor(WebClient tossPaymentWebClient) {
		this.tossPaymentWebClient = tossPaymentWebClient;
	}

	public Mono<String> execute(final String paymentKey, final String orderId, final String amount) {
		return tossPaymentWebClient.post()
			.uri(uri)
			.bodyValue("""
				{
					"paymentKey": "%s",
					"orderId": "%s",
					"amount": %s
				}
				""".formatted(paymentKey, orderId, amount)
			)
			.retrieve()
			.onStatus(
				HttpStatusCode::is4xxClientError,
				response -> response.bodyToMono(String.class)
					.doOnNext(body -> System.out.println("Toss confirm 4xx error: " + body))
					.flatMap(body -> Mono.error(new RuntimeException("Toss API 4xx error: " + body)))
			)
			.bodyToMono(String.class);
	}
}
