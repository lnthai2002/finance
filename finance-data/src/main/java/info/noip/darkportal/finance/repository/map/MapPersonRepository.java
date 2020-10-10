package info.noip.darkportal.finance.repository.map;

import info.noip.darkportal.finance.model.Person;
import info.noip.darkportal.finance.repository.PersonRepository;

public class MapPersonRepository extends MapCrudRepository<Person> implements PersonRepository {
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
