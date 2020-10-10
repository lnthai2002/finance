package info.noip.darkportal.finance.repository.map;

import info.noip.darkportal.finance.model.PaymentType;
import repository.CrudRepository;

public class MapPaymentTypeRepository extends MapCrudRepository<PaymentType> implements CrudRepository<PaymentType> {
    @Override
    public PaymentType save(PaymentType object) {
        return super.save(object.getId(), object);
    }
}
