package info.noip.darkportal.finance.data.repository;

import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.service.jpa.CategoryMother;
import info.noip.darkportal.finance.data.service.jpa.PaymentTypeMother;
import info.noip.darkportal.finance.data.service.jpa.PersonMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void save() {
        //given
        Payment payment = Payment.Builder.aPayment()
                .withTransactionDate(LocalDate.now())
                .withAmountCents(10000L)
                .withPaymentType(PaymentTypeMother.complete())
                .withCategory(CategoryMother.complete())
                .withPerson(PersonMother.complete())
                .build();

        //act
        payment = paymentRepository.save(payment);

        //validate
        assertEquals(payment, paymentRepository.findById(payment.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not found")));
    }
}