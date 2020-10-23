package info.noip.darkportal.finance.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Payment extends BaseEntity {
    private LocalDate transactionDate;

    @Setter(AccessLevel.NONE)//exclude setter from auto generation for this field
    private Long amountCents;

    @ManyToOne
    private PaymentType paymentType;

    @ManyToOne
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties({"expenses", "incomes"})
    private Person person;

    public void setAmountCents(Long amountCents) {
        if(getCategory() == null) {
            throw new IllegalStateException("Cannot set amount without a category");
        }
        this.amountCents = Math.abs(amountCents) * getCategory().getEffect();
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
