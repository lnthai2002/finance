package info.noip.darkportal.finance.data.model;

import java.util.Set;

public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<Payment> incomes;
    private Set<Payment> expenses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
