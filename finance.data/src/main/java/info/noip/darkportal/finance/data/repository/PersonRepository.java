package info.noip.darkportal.finance.data.repository;

import info.noip.darkportal.finance.data.model.Person;

public interface PersonRepository extends BaseRepository<Person, Long> {
    @Override
    Iterable<Person> findAll();
}
