package info.noip.darkportal.finance.data.service;

import info.noip.darkportal.finance.data.model.Person;

public interface PersonService extends CrudService<Person> {
    void patch(Person person);
}