package info.noip.darkportal.finance.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
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

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
