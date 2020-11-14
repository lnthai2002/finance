package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.PaymentType;

import java.time.LocalDate;

public class PaymentMother {
    /**
     * Create a complete Payment object
     * */
    public static Payment complete() {
        return new Payment(CategoryMother.complete(), 15000L)
                .transactionDate(LocalDate.now())
                .paymentType(PaymentTypeMother.complete())
                .person(PersonMother.complete());
    }
}
