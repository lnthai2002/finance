package repository.map;

import info.noip.darkportal.finance.model.Payment;
import repository.CrudRepository;

public class MapPaymentRepository extends MapCrudRepository<Payment> implements CrudRepository<Payment> {
    @Override
    public Payment save(Payment object) {
        return super.save(object.getId(), object);
    }
}
