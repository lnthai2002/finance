package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.Person;

import java.util.Collections;

public class PersonMother {
    public static Person complete() {
        return new Person()
                .firstName("Jamie")
                .lastName("Lanister")
                .incomes(Collections.emptyList())
                .expenses(Collections.emptyList());
    }
}
