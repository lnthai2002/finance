package info.noip.darkportal.finance.data.repository.map;

import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MapCategoryRepository extends MapCrudRepository<Category> implements CategoryRepository {
    @Override
    public Category save(Category object) {
        Long id = object.getId();
        synchronized (map) {
            if (id == null) {
                object.setId(getNextId());
            }
            return super.save(object.getId(), object);
        }
    }

    @Override
    public Set<Category> findAllExpenseCategories() {
        return super.findAll().stream().filter(category -> category.getEffect() < 0).collect(Collectors.toSet());
    }

    @Override
    public Set<Category> findAllIncomeCategories() {
        return super.findAll().stream().filter(category -> category.getEffect() > 0).collect(Collectors.toSet());
    }
}
