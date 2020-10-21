package info.noip.darkportal.finance.data.service;

import java.util.Collection;

public interface CrudService<T> {
    T save(T object);
    Collection<T> findAll();
    T findById(Long id);
    void delete(T object);
    void deleteById(Long id);
}
