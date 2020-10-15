package info.noip.darkportal.finance.data.model;

public class PaymentType {
    private Long id;
    private String name;

    public PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
