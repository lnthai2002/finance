package info.noip.darkportal.finance.api.v1.mapper;

import info.noip.darkportal.finance.api.v1.dto.PersonResponseDTO;
import info.noip.darkportal.finance.data.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public PersonResponseDTO fromDomain(Person person) {
        return new PersonResponseDTO(person.getFirstName(),
                person.getLastName());
    }
}
