package repository.map;

import info.noip.darkportal.finance.model.Income;
import repository.CrudRepository;

public class MapIncomeRepository extends MapCrudRepository<Income> implements CrudRepository<Income> {

    @Override
    public Income save(Income object) {
        return super.save(object.getId(), object);
    }
}
