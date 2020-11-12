package info.noip.darkportal.finance.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true, fluent = true)//setter return the object and remove set/get prefix from setters and getter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
}
