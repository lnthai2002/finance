package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.Messages;
import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.repository.PersonRepository;
import info.noip.darkportal.finance.data.service.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile({"default", "jpa"})
public class JpaPersonService implements PersonService {
    private PersonRepository personRepository;
    private Messages messages;

    public JpaPersonService(PersonRepository personRepository, Messages messages) {
        this.personRepository = personRepository;
        this.messages = messages;
    }

    @Override
    public Person save(Person object) {
        return personRepository.save(object);
    }

    @Override
    public Collection<Person> findAll() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messages.get("jpa.entityNotFound")));
    }

    @Override
    public void delete(Person object) {
        personRepository.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException(messages.get("jpa.entityNotFound"));
        }
    }

    @Override
    @Transactional
    public void update(Person object) {
        if (personRepository.existsById(object.id())) {//update
            personRepository.save(object);
        }
        else {
            throw new EntityNotFoundException("Person not existed");
        }
    }

    @Override
    @Transactional
    public void patch(Person personWithNewInfo) {
        Person person = personRepository.findById(personWithNewInfo.id())
                .orElseThrow(() -> new EntityNotFoundException("Person not exists"));
        if (personWithNewInfo.firstName() != null) {
            person.firstName(personWithNewInfo.firstName());
        }
        if (personWithNewInfo.lastName() != null) {
            person.lastName(personWithNewInfo.lastName());
        }
        personRepository.save(person);
    }
}
