package repository.map;

import info.noip.darkportal.finance.model.ExpenseCategory;
import repository.CrudRepository;

public class MapExpenseCategoryRepository extends MapCrudRepository<ExpenseCategory> implements CrudRepository<ExpenseCategory> {
    @Override
    public ExpenseCategory save(ExpenseCategory object) {
        return super.save(object.getId(), object);
    }
}
