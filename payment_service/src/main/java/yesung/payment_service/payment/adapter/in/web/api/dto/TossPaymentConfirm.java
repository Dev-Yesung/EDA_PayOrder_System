package yesung.payment_service.payment.adapter.in.web.api.dto;

public class TossPaymentConfirm {

	public record Request(
		String paymentKey,
		String orderId,
		Long amount
	) {
		public String getAmountToString() {
			return String.valueOf(amount);
		}
	}
}
