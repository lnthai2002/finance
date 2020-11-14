package info.noip.darkportal.finance.data.service.map;

import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.service.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mapdata")
public class MapPersonService extends MapCrudService<Person> implements PersonService {
    @Override
    public Person save(Person object) {
        Long id = object.id();
        synchronized (map) {
            if (id == null) {
                object.id(getNextId());
            }
            return super.save(object.id(), object);
        }
    }

    @Override
    public void update(Person object) {
        if (map.containsKey(object.id())) {
            map.put(object.id(), object);
        }
        else {
            throw new EntityNotFoundException("Person not exists");
        }
    }

    @Override
    public void patch(Person personWithNewInfo) {
        Person existingPerson = map.get(personWithNewInfo.id());
        if (existingPerson != null) {
            if (personWithNewInfo.firstName() != null) {
                existingPerson.firstName(personWithNewInfo.firstName());
            }
            if (personWithNewInfo.lastName() != null) {
                existingPerson.lastName(personWithNewInfo.lastName());
            }
        }
    }
}
