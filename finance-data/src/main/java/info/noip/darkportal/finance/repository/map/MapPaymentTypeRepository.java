package info.noip.darkportal.finance.repository.map;

import info.noip.darkportal.finance.model.PaymentType;
import info.noip.darkportal.finance.repository.PaymentTypeRepository;

public class MapPaymentTypeRepository extends MapCrudRepository<PaymentType> implements PaymentTypeRepository {
    @Override
    public PaymentType save(PaymentType object) {
        Long id = object.getId();
        synchronized (map) {
            if (id == null) {
                object.setId(getNextId());
            }
            return super.save(object.getId(), object);
        }
    }
}
