package info.noip.darkportal.finance.data.service.map;

import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.service.PaymentTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mapdata")
public class MapPaymentTypeService extends MapCrudService<PaymentType> implements PaymentTypeService {
    @Override
    public PaymentType save(PaymentType object) {
        Long id = object.id();
        synchronized (map) {
            if (id == null) {
                object.id(getNextId());
            }
            return super.save(object.id(), object);
        }
    }
}
