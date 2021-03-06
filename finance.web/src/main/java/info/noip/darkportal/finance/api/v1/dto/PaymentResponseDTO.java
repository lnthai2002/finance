package info.noip.darkportal.finance.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponseDTO {
    private LocalDate transactionDate;
    private Long amountCents;
    private String paymentType;
    private String category;
    private Long ownerId;
}
