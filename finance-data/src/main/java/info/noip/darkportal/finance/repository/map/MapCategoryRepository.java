package info.noip.darkportal.finance.repository.map;

import info.noip.darkportal.finance.model.Category;
import repository.CrudRepository;

public class MapCategoryRepository extends MapCrudRepository<Category> implements CrudRepository<Category> {
    @Override
    public Category save(Category object) {
        return super.save(object.getId(), object);
    }
}