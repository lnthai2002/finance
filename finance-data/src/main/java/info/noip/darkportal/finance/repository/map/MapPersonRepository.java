package info.noip.darkportal.finance.repository.map;

import info.noip.darkportal.finance.model.Person;
import repository.CrudRepository;

public class MapPersonRepository extends MapCrudRepository<Person> implements CrudRepository<Person> {
    @Override
    public Person save(Person object) {
        return super.save(object.getId(), object);
    }
}
