package info.noip.darkportal.finance.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Category extends BaseEntity {
    private String name;

    /**
     * Category with negative effect is a debit, otherwise is a credit
     */
    private Integer effect;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
