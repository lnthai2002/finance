package info.noip.darkportal.finance.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate transactionDate;
    private Long amountCents;

    @ManyToOne
    private PaymentType paymentType;

    @ManyToOne
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties({"expenses", "incomes"})
    private Person person;

    /**
     * This constructor should only be used by ORM/JPA
     */
    protected Payment() {
    }

    /**
     * Business logic should use this constructor
     * */
    public Payment(Category category, Long amountCents) {
        this.category = category;
        if(category == null || category.effect() == null) {
            throw new IllegalStateException("Cannot determine if this is an expense or income");
        }
        this.amountCents = Math.abs(amountCents) * category.effect();
    }

    public Payment amountCents(Long amount) {
        if(category == null || category.effect() == null) {
            throw new IllegalStateException("Cannot determine if this is an expense or income");
        }
        this.amountCents = Math.abs(amount) * category.effect();
        return this;
    }
}
