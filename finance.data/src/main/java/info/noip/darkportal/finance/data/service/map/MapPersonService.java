package info.noip.darkportal.finance.data.service.map;

import info.noip.darkportal.finance.data.model.Person;
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
}
