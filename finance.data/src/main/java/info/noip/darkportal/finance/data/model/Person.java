package info.noip.darkportal.finance.data.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "person")
    private List<Payment> incomes;

    @OneToMany(mappedBy = "person")
    private List<Payment> expenses;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Payment> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Payment> incomes) {
        this.incomes = incomes;
    }

    public List<Payment> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Payment> expenses) {
        this.expenses = expenses;
    }
}
