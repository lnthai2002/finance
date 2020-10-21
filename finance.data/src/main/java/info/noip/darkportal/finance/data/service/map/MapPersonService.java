package info.noip.darkportal.finance.data.service.map;

import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.service.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mapdata")
public class MapPersonService extends MapCrudService<Person> implements PersonService {
    @Override
    public Person save(Person object) {
        Long id = object.getId();
        synchronized (map) {
            if (id == null) {
                object.setId(getNextId());
            }
            return super.save(object.getId(), object);
        }
    }
}
