package info.noip.darkportal.finance.data.repository;

import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.model.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)//may not need this as of SpringBoot 2.1
@DataJpaTest
class PaymentRepositoryTest {

    /**
     *  All the JPA repositories are interface (no constructor) extending Spring Repository,
     *  thus they cant be injected by constructor
     */
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void save() {
        //given
        Category category = categoryRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Not found"));

        Payment payment = new Payment();
        LocalDate now = LocalDate.now();
        payment.setTransactionDate(now);
        payment.setCategory(category);
        payment.setAmountCents(98765L);

        //act
        payment = paymentRepository.save(payment);

        //validate
        assertEquals(payment, paymentRepository.findById(payment.getId()));
    }
}