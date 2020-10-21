package info.noip.darkportal.finance.data.service.map;

import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.service.PaymentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("mapdata")
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
    public List<Payment> findAllExpenses() {
        List<Payment> payments = super.findAll().stream().filter(payment -> payment.getAmountCents() < 0).collect(Collectors.toList());
        payments.sort(getPaymentDateComparator());
        return payments;
    }

    @Override
    public List<Payment> findAllIncomes() {
        List<Payment> payments = super.findAll().stream().filter(payment -> payment.getAmountCents() > 0).collect(Collectors.toList());
        payments.sort(getPaymentDateComparator());
        return payments;
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = super.findAll().stream().collect(Collectors.toList());
        payments.sort(getPaymentDateComparator());
        return payments;
    }

    private Comparator<Payment> getPaymentDateComparator() {
        return new Comparator<Payment>() {
            @Override
            public int compare(Payment payment1, Payment payment2) {
                return payment1.getTransactionDate().compareTo(payment2.getTransactionDate());
            }
        };
    }
}
