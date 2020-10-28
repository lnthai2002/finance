package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.Person;

import java.util.Collections;

public class PersonMother {
    public static Person complete() {
        return Person.Builder.aPerson()
                .withFirstName("Jamie")
                .withLastName("Lanister")
                .withIncomes(Collections.emptyList())
                .withExpenses(Collections.emptyList())
                .build();
    }
}
