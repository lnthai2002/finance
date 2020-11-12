package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.PaymentType;

public class PaymentTypeMother {
    public static PaymentType complete() {
        return new PaymentType()
                .name("Cash");
    }
}
