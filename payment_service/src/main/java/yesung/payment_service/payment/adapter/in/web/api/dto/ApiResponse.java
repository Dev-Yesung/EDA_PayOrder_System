package yesung.payment_service.payment.adapter.in.web.api.dto;

import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
	int status,
	String message,
	T data
) {

	public ApiResponse() {
		this(200, "", null);
	}

	public ApiResponse(T data) {
		this(200, "", data);
	}

	public ApiResponse(int status, T data) {
		this(status, "", data);
	}

	public ApiResponse(int status, String message) {
		this(status, message, null);
	}

	public static <T> ApiResponse<T> with(HttpStatus httpStatus, String message, T data) {
		return new ApiResponse<>(httpStatus.value(), message, data);
	}
}
