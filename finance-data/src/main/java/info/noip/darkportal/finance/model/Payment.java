package info.noip.darkportal.finance.model;

import java.util.Date;

public class Payment {
    private long id;

    private Date transaction_date;
    private long amount_cents;
    private PaymentType paymentType;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount_cents=" + amount_cents +
                '}';
    }

}
