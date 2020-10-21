package info.noip.darkportal.finance.data.service.map;

import java.util.*;

public abstract class MapCrudService<T> {
    protected Map<Long, T> map = new HashMap<>();

    public T save(Long id, T object) {
        map.put(id, object);
        return object;
    }

    public Collection<T> findAll() {
        return map.values();
    }

    public T findById(Long id) {
        return map.get(id);
    }

    public void delete(T object) {
        map.entrySet().removeIf(pair -> pair.getValue().equals(object));
    }

    public void deleteById(Long id) {
        map.remove(id);
    }

    //implementation detail
    public Long getNextId() {
        return Long.valueOf(map.size());
    }
}
