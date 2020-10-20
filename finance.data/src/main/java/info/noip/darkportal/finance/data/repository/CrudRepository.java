package info.noip.darkportal.finance.data.repository;

import java.util.Collection;

public interface CrudRepository<T> {
    T save(T object);
    Collection<T> findAll();
    T findById(Long id);
    void delete(T object);
    void deleteById(Long id);
}
