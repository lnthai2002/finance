package info.noip.darkportal.finance.repository.map;

import info.noip.darkportal.finance.model.Person;
import info.noip.darkportal.finance.repository.PersonRepository;

public class MapPersonRepository extends MapCrudRepository<Person> implements PersonRepository {
    @Override
    public Person save(Person object) {
        return super.save(object.getId(), object);
    }
}
