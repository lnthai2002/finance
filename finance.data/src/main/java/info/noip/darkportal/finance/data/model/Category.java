package info.noip.darkportal.finance.data.model;

import java.util.Set;

public class Category {
    private Long id;
    private String name;
    private Set<Payment> payments;
    /**
     * Category with negative effect is a debit, otherwise is a credit
     */
    private Integer effect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEffect() {
        return effect;
    }

    public void setEffect(Integer effect) {
        this.effect = effect;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Category(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
