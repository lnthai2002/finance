package info.noip.darkportal.finance.data.service.map;

import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.service.PaymentService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("mapdata")
public class MapPaymentService extends MapCrudService<Payment> implements PaymentService {

    @Override
    public Payment save(Payment object) {
        Long id = object.id();
        synchronized (map) {
            if (id == null) {
                object.id(getNextId());
            }
            return super.save(object.id(), object);
        }
    }

    @Override
    public void update(Payment object) {
        if (map.containsKey(object.id())) {
            map.put(object.id(), object);
        }
        else {
            throw new EntityNotFoundException("Payment not exists");
        }
    }

    @Override
    public List<Payment> findAllExpenses(Long personId) {
        List<Payment> payments = super.findAll().stream()
                .filter(payment -> personId == payment.person().id())
                .filter(payment -> payment.amountCents() < 0)
                .collect(Collectors.toList());
        payments.sort(getPaymentDateComparator());
        return payments;
    }

    @Override
    public List<Payment> findAllIncomes(Long personId) {
        List<Payment> payments = super.findAll().stream()
                .filter(payment -> personId == payment.person().id())
                .filter(payment -> payment.amountCents() > 0)
                .collect(Collectors.toList());
        payments.sort(getPaymentDateComparator());
        return payments;
    }

    @Override
    public List<Payment> findAll(Long personId) {
        List<Payment> payments = super.findAll().stream()
                .filter(payment -> personId == payment.person().id())
                .collect(Collectors.toList());
        payments.sort(getPaymentDateComparator());
        return payments;
    }

    private Comparator<Payment> getPaymentDateComparator() {
        return new Comparator<Payment>() {
            @Override
            public int compare(Payment payment1, Payment payment2) {
                return payment1.transactionDate().compareTo(payment2.transactionDate());
            }
        };
    }
}
