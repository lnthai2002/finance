package info.noip.darkportal.finance.data.model;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class PaymentType extends BaseEntity {
    private String name;

    public PaymentType() {
    }

    public PaymentType(String name) {
        this.name = name;
    }

    public static final class Builder {
        private String name;

        private Builder() {
        }

        public static Builder aPaymentType() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public PaymentType build() {
            return new PaymentType(name);
        }
    }
}
