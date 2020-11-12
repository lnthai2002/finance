package info.noip.darkportal.finance.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class PaymentType extends BaseEntity {
    private String name;

    public PaymentType() {
    }
}
