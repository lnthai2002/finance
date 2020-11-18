package info.noip.darkportal.finance.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private String transactionDate;
    private Long amountCents;
    private Long paymentTypeId;
    private Long categoryId;
    private Long ownerId;
}
