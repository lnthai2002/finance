package info.noip.darkportal.finance.data.repository.map;

import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.repository.PersonRepository;
import org.springframework.stereotype.Repository;

@Repository
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
