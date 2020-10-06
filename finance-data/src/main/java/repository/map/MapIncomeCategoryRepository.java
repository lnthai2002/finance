package repository.map;

import info.noip.darkportal.finance.model.IncomeCategory;
import repository.CrudRepository;

public class MapIncomeCategoryRepository extends MapCrudRepository<IncomeCategory> implements CrudRepository<IncomeCategory> {
    @Override
    public IncomeCategory save(IncomeCategory object) {
        return super.save(object.getId(), object);
    }
}
