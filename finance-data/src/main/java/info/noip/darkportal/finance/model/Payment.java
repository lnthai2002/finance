package info.noip.darkportal.finance.model;

import java.util.Date;

public class Payment {
    private long id;

    private Date transaction_date;
    private long amount_cents;
    private PaymentType paymentType;
    private Category category;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount_cents=" + amount_cents +
                '}';
    }

}
