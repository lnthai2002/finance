package info.noip.darkportal.finance.api.v1.mapper;

import info.noip.darkportal.finance.api.v1.dto.PersonRequestDTO;
import info.noip.darkportal.finance.api.v1.dto.PersonResponseDTO;
import info.noip.darkportal.finance.data.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public PersonResponseDTO fromDomain(Person person) {
        return new PersonResponseDTO(person.getFirstName(),
                person.getLastName());
    }

    public Person fromDto(PersonRequestDTO dto) {
        Person person = Person.Builder.aPerson()
                .withFirstName(dto.getFirstName())
                .withLastName(dto.getLastName())
                .build();
        return person;
    }
}
