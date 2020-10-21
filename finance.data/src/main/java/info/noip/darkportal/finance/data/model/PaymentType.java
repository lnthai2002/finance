package info.noip.darkportal.finance.data.model;

import javax.persistence.Entity;

@Entity
public class PaymentType extends BaseEntity {
    private String name;

    public PaymentType() {
    }

    public PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
