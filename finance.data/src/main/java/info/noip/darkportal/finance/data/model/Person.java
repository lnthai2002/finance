package info.noip.darkportal.finance.data.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "person")
    private Set<Payment> incomes;

    @OneToMany(mappedBy = "person")
    private Set<Payment> expenses;

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

    public Set<Payment> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Payment> incomes) {
        this.incomes = incomes;
    }

    public Set<Payment> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Payment> expenses) {
        this.expenses = expenses;
    }
}
