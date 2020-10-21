package info.noip.darkportal.finance.data.service.map;

import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.service.CategoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile("mapdata")
public class MapCategoryService extends MapCrudService<Category> implements CategoryService {
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
