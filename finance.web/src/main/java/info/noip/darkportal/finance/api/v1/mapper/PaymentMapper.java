package info.noip.darkportal.finance.api.v1.mapper;

import info.noip.darkportal.finance.api.v1.dto.PaymentResponseDTO;
import info.noip.darkportal.finance.data.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentResponseDTO fromDomain(Payment payment) {
        return new PaymentResponseDTO(payment.transactionDate(),
                payment.amountCents(),
                payment.paymentType().name(),
                payment.category().name(),
                payment.person().getId()
        );
    }
}
