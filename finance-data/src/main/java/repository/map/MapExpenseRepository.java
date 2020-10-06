package repository.map;

import info.noip.darkportal.finance.model.Expense;
import repository.CrudRepository;

public class MapExpenseRepository extends MapCrudRepository<Expense> implements CrudRepository<Expense> {
    @Override
    public Expense save(Expense object) {
        return super.save(object.getId(), object);
    }
}
