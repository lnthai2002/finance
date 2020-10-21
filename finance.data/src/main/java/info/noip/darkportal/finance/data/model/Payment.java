package info.noip.darkportal.finance.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Payment extends BaseEntity {
    private LocalDate transactionDate;
    private Long amountCents;

    @ManyToOne
    private PaymentType paymentType;

    @ManyToOne
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties({"expenses", "incomes"})
    private Person person;

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
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
                "id=" + getId() +
                ", date=" + transactionDate.toString() +
                ", amount_cents=" + amountCents +
                ", payment type=" + paymentType.getName() +
                ", category=" + category.getName() +
                ", owner=" + person.getFirstName() + " " + person.getLastName() +
                '}';
    }

}
