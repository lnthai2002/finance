package info.noip.darkportal.finance.data.repository.map;

import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.repository.PaymentTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
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