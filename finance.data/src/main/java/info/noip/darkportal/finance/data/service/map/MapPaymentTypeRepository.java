package info.noip.darkportal.finance.data.service.map;

import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.service.PaymentTypeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mapdata")
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
