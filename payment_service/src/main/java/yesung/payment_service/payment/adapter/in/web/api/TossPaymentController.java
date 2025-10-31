package yesung.payment_service.payment.adapter.in.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import yesung.payment_service.payment.adapter.in.web.api.dto.ApiResponse;
import yesung.payment_service.payment.adapter.in.web.api.dto.TossPaymentConfirm;
import yesung.payment_service.payment.adapter.out.web.executor.TossPaymentExecutor;
import yesung.payment_service.payment.common.WebAdapter;

@WebAdapter
@RequestMapping("/v1/toss")
@RestController
public class TossPaymentController {

	private final TossPaymentExecutor tossPaymentExecutor;

	public TossPaymentController(TossPaymentExecutor tossPaymentExecutor) {
		this.tossPaymentExecutor = tossPaymentExecutor;
	}

	@PostMapping("/confirm")
	public Mono<ResponseEntity<ApiResponse<String>>> confirm(
		@RequestBody TossPaymentConfirm.Request request
	) {
		return tossPaymentExecutor.execute(
				request.paymentKey(),
				request.orderId(),
				request.getAmountToString()
			)
			.map(it -> ResponseEntity.ok()
				.body(ApiResponse.with(HttpStatus.OK, "Ok", it))
			);
	}
}
