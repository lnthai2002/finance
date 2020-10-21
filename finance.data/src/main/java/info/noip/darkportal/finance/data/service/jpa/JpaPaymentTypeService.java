package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.repository.PaymentTypeRepository;
import info.noip.darkportal.finance.data.service.PaymentTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile({"default", "jpa"})
public class JpaPaymentTypeService implements PaymentTypeService {
    private PaymentTypeRepository paymentTypeRepository;
    private Messages messages;

    public JpaPaymentTypeService(PaymentTypeRepository paymentTypeRepository, Messages messages) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.messages = messages;
    }

    @Override
    public PaymentType save(PaymentType object) {
        return paymentTypeRepository.save(object);
    }

    @Override
    public Collection<PaymentType> findAll() {
        return StreamSupport.stream(paymentTypeRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public PaymentType findById(Long id) {
        return paymentTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messages.get("jpa.entityNotFound")));
    }

    @Override
    public void delete(PaymentType object) {
        paymentTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        paymentTypeRepository.deleteById(id);
    }
}
