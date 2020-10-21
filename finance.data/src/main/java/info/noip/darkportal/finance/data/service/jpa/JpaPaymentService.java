package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.repository.PaymentRepository;
import info.noip.darkportal.finance.data.service.PaymentService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile({"default", "jpa"})
public class JpaPaymentService implements PaymentService {
    private PaymentRepository paymentRepository;
    private Messages messages;

    public JpaPaymentService(PaymentRepository paymentRepository, Messages messages) {
        this.paymentRepository = paymentRepository;
        this.messages = messages;
    }

    @Override
    public Payment save(Payment object) {
        return paymentRepository.save(object);
    }

    @Override
    public List<Payment> findAll() {
        return StreamSupport.stream(
                paymentRepository.findAll(
                        Sort.by(Sort.Direction.DESC, "transactionDate"))
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messages.get("jpa.entityNotFound")));
    }

    @Override
    public void delete(Payment object) {
        paymentRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<Payment> findAllExpenses() {
        return StreamSupport.stream(
                paymentRepository.findExpenses().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findAllIncomes() {
        return StreamSupport.stream(
                paymentRepository.findByCategoryEffect(1).spliterator(), false)
                .collect(Collectors.toList());
    }
}