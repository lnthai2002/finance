package info.noip.darkportal.finance.data.service.map;

import info.noip.darkportal.finance.data.model.Payment;
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
    public List<Payment> findAll(Long personId) {
        List<Payment> payments = super.findAll().stream()
                .filter(payment -> personId == payment.getPerson().getId())
                .collect(Collectors.toList());
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
