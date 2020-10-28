package info.noip.darkportal.finance.data.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Category extends BaseEntity {
    private String name;

    /**
     * Category with negative effect is a debit, otherwise is a credit
     */
    private Integer effect;

    public Category() {
    }

    public Category(String name, Integer effect) {
        this.name = name;
        this.effect = effect;
    }

    public static final class Builder {
        private String name;
        private Integer effect;

        private Builder() {
        }

        public static Builder aCategory() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEffect(Integer effect) {
            this.effect = effect;
            return this;
        }

        public Category build() {
            return new Category(name, effect);
        }
    }
}
