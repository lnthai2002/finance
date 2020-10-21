package info.noip.darkportal.finance.data.model;

import javax.persistence.*;

@Entity
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

    public Integer getEffect() {
        return effect;
    }

    public void setEffect(Integer effect) {
        this.effect = effect;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
