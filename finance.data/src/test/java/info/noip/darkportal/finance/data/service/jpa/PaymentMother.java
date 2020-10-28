package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.PaymentType;

import java.time.LocalDate;

public class PaymentMother {
    /**
     * Create a complete Payment object
     * */
    public static Payment complete() {
        return Payment.Builder.aPayment()
                .withTransactionDate(LocalDate.now())
                .withAmountCents(15000L)
                .withPaymentType(PaymentTypeMother.complete())
                .withCategory(CategoryMother.complete())
                .withPerson(PersonMother.complete())
                .build();

    }
}
