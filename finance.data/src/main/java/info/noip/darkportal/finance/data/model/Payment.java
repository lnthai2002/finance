package info.noip.darkportal.finance.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
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

    public Payment() {
    }

    public Payment(LocalDate transactionDate, Long amountCents, PaymentType paymentType, Category category, Person person) {
        this.transactionDate = transactionDate;
        this.paymentType = paymentType;
        this.category = category;
        if(this.category == null || this.category.getEffect() == null) {
            throw new IllegalStateException("Cannot determine if this is an expense or income");
        }
        this.amountCents = Math.abs(amountCents) * getCategory().getEffect();
        this.person = person;
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

    /**
     * Make this inner class static so it can be instantiated without an instance of outer class
     * */
    public static final class Builder {
        private LocalDate transactionDate;
        private Long amountCents;
        private PaymentType paymentType;
        private Category category;
        private Person person;

        private Builder() {
        }

        public static Builder aPayment() {
            return new Builder();
        }

        public Builder withTransactionDate(LocalDate transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public Builder withAmountCents(Long amountCents) {
            this.amountCents = amountCents;
            return this;
        }

        public Builder withPaymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withPerson(Person person) {
            this.person = person;
            return this;
        }

        public Payment build() {
            return new Payment(transactionDate, amountCents, paymentType, category, person);
        }
    }
}
