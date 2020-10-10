package info.noip.darkportal.finance.repository.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class MapCrudRepository<T> {
    private Map<Long, T> map = new HashMap<>();

    public T save(Long id, T object) {
        map.put(id, object);
        return object;
    }

    public Set<T> findAll() {
        return new HashSet<>(map.values());
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
}