package yesung.payment_service.payment.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target(value = ElementType.TYPE)
@Component
public @interface WebAdapter {
}
