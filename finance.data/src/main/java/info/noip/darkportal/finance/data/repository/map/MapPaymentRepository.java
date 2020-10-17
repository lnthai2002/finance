package info.noip.darkportal.finance.data.repository.map;

import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MapPaymentRepository extends MapCrudRepository<Payment> implements PaymentRepository {

    @Override
    public Payment save(Payment object) {
        Long id = object.getId();
        synchronized (map) {
            if (id == null) {
                object.setId(getNextId());
            }
            return super.save(object.getId(), object);
        }
    }

    @Override
    public Set<Payment> findAllExpenses() {
        return super.findAll().stream().filter(payment -> payment.getAmountCents() < 0).collect(Collectors.toSet());
    }

    @Override
    public Set<Payment> findAllIncomes() {
        return super.findAll().stream().filter(payment -> payment.getAmountCents() > 0).collect(Collectors.toSet());
    }
}
