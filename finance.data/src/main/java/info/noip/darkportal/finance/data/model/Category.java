package info.noip.darkportal.finance.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /**
     * Category with negative effect is a debit, otherwise is a credit
     */
    private Integer effect;

    public Category() {
    }
}
