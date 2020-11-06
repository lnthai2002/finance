package info.noip.darkportal.finance.api.v1.mapper;

import info.noip.darkportal.finance.api.v1.dto.PaymentResponseDTO;
import info.noip.darkportal.finance.data.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentResponseDTO fromDomain(Payment payment) {
        return new PaymentResponseDTO(payment.getTransactionDate(),
                payment.getAmountCents(),
                payment.getPaymentType().getName(),
                payment.getCategory().getName(),
                payment.getPerson().getId()
        );
    }
}
