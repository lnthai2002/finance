package info.noip.darkportal.finance.data.repository;

import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.service.jpa.CategoryMother;
import info.noip.darkportal.finance.data.service.jpa.PaymentTypeMother;
import info.noip.darkportal.finance.data.service.jpa.PersonMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        Person employee1 = Person.Builder.aPerson()
                .withFirstName("John")
                .withLastName("Doe")
                .build();
        personRepository.save(employee1);

        PaymentType cash = PaymentType.Builder.aPaymentType()
                .withName("Cash")
                .build();
        paymentTypeRepository.save(cash);

        Category grocery = Category.Builder.aCategory()
                .withName("Grocery")
                .withEffect(-1)
                .build();
        categoryRepository.save(grocery);

        Category salary = Category.Builder.aCategory()
                .withName("Salary")
                .withEffect(1)
                .build();
        categoryRepository.save(salary);

        Payment expense = Payment.Builder.aPayment()
                .withTransactionDate(LocalDate.now())
                .withAmountCents(20000L)
                .withPerson(employee1)
                .withCategory(grocery)
                .withPaymentType(cash)
                .build();
        paymentRepository.save(expense);

        Payment income = Payment.Builder.aPayment()
                .withTransactionDate(LocalDate.now())
                .withAmountCents(100000L)
                .withPerson(employee1)
                .withCategory(salary)
                .withPaymentType(cash)
                .build();
        paymentRepository.save(income);
    }

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

    @Test
    void findExpenses() {
        //given
        Iterable<Person> people = personRepository.findAll();
        Person person = people.iterator().next();

        //act
        Iterable<Payment> exps = paymentRepository.findExpenses(person.getId());

        //validate
        assertNotNull(exps);
        for (Payment exp : exps) {
            assertTrue(exp.getAmountCents() < 0L);
            assertTrue(exp.getCategory().getEffect() < 0);
        }
    }

    @Test
    void findIncomes() {
        //given
        Iterable<Person> people = personRepository.findAll();
        Person person = people.iterator().next();

        //act
        Iterable<Payment> incomes = paymentRepository.findIncomes(person.getId());

        //validate
        assertNotNull(incomes);
        for (Payment income : incomes) {
            assertTrue(income.getAmountCents() > 0L);
            assertTrue(income.getCategory().getEffect() > 0);
        }
    }
}