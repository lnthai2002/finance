package info.noip.darkportal.finance.api.v1.mapper;

import info.noip.darkportal.finance.api.v1.dto.PersonRequestDTO;
import info.noip.darkportal.finance.api.v1.dto.PersonResponseDTO;
import info.noip.darkportal.finance.data.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public PersonResponseDTO fromDomain(Person person) {
        return new PersonResponseDTO(person.firstName(),
                person.lastName());
    }

    public Person fromDto(PersonRequestDTO dto) {
        Person person = new Person()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName());
        return person;
    }
}
