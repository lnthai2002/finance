package info.noip.darkportal.finance.data.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    private Long id;

    private Date transactionDate;
    private Long amountCents;
    private PaymentType paymentType;
    private Category category;
    private Person person;

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getAmountCents() {
        return amountCents;
    }

    public void setAmountCents(Long amountCents) {
        if(getCategory() == null) {
            throw new IllegalStateException("Cannot set amount without a category");
        }
        this.amountCents = Math.abs(amountCents) * getCategory().getEffect();
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        DateFormat df = new SimpleDateFormat();
        return "Payment{" +
                "id=" + id +
                ", date=" + df.format(transactionDate) +
                ", amount_cents=" + amountCents +
                ", payment type=" + paymentType.getName() +
                ", category=" + category.getName() +
                ", owner=" + person.getFirstName() + " " + person.getLastName() +
                '}';
    }

}
