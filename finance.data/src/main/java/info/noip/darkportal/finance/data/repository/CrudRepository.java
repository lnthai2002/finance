package info.noip.darkportal.finance.data.repository;

import java.util.Set;

public interface CrudRepository<T> {
    T save(T object);
    Set<T> findAll();
    T findById(Long id);
    void delete(T object);
    void deleteById(Long id);
}
