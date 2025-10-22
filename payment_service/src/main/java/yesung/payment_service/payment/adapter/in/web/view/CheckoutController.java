package yesung.payment_service.payment.adapter.in.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Mono;
import yesung.payment_service.payment.common.WebAdapter;

@Controller
@WebAdapter
public class CheckoutController {

	@GetMapping("/")
	public Mono<String> checkoutPage() {
		return Mono.just("checkout");
	}
}
