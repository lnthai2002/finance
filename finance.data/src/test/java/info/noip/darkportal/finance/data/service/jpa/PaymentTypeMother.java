package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.PaymentType;

public class PaymentTypeMother {
    public static PaymentType complete() {
        return PaymentType.Builder.aPaymentType()
                .withName("Cash")
                .build();
    }
}
