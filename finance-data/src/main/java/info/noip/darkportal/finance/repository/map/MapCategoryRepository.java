package info.noip.darkportal.finance.repository.map;

import info.noip.darkportal.finance.model.Category;
import info.noip.darkportal.finance.repository.CategoryRepository;

import java.util.Set;
import java.util.stream.Collectors;
public class MapCategoryRepository extends MapCrudRepository<Category> implements CategoryRepository {
    @Override
    public Category save(Category object) {
        return super.save(object.getId(), object);
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
