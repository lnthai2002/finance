package info.noip.darkportal.finance.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PaymentType extends BaseEntity {
    private String name;

    public PaymentType() {
    }

    public PaymentType(String name) {
        this.name = name;
    }
}
