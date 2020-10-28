package info.noip.darkportal.finance.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "person")
    @JsonIgnoreProperties("person")
    private List<Payment> incomes;

    @OneToMany(mappedBy = "person")
    @JsonIgnoreProperties("person")
    private List<Payment> expenses;

    public Person() {
    }

    public Person(String firstName, String lastName, List<Payment> incomes, List<Payment> expenses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.incomes = incomes;
        this.expenses = expenses;
    }


    public static final class Builder {
        private String firstName;
        private String lastName;
        private List<Payment> incomes;
        private List<Payment> expenses;

        private Builder() {
        }

        public static Builder aPerson() {
            return new Builder();
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withIncomes(List<Payment> incomes) {
            this.incomes = incomes;
            return this;
        }

        public Builder withExpenses(List<Payment> expenses) {
            this.expenses = expenses;
            return this;
        }

        public Person build() {
            return new Person(firstName, lastName, incomes, expenses);
        }
    }
}
