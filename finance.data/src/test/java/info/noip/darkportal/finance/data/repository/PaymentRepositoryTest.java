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
        Person employee1 = new Person()
                .firstName("John")
                .lastName("Doe");
        personRepository.save(employee1);

        PaymentType cash = new PaymentType()
                .name("Cash");
        paymentTypeRepository.save(cash);

        Category grocery = new Category()
                .name("Grocery")
                .effect(-1);
        categoryRepository.save(grocery);

        Category salary = new Category()
                .name("Salary")
                .effect(1);
        categoryRepository.save(salary);

        Payment expense = new Payment()
                .transactionDate(LocalDate.now())
                .category(grocery)
                .amountCents(20000L)
                .person(employee1)
                .paymentType(cash);
        paymentRepository.save(expense);

        Payment income = new Payment()
                .transactionDate(LocalDate.now())
                .category(salary)
                .amountCents(100000L)
                .person(employee1)
                .paymentType(cash);
        paymentRepository.save(income);
    }

    @Test
    void save() {
        //given
        Payment payment = new Payment()
                .transactionDate(LocalDate.now())
                .category(CategoryMother.complete())
                .amountCents(10000L)
                .paymentType(PaymentTypeMother.complete())
                .person(PersonMother.complete());

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
            assertTrue(exp.amountCents() < 0L);
            assertTrue(exp.category().effect() < 0);
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
            assertTrue(income.amountCents() > 0L);
            assertTrue(income.category().effect() > 0);
        }
    }
}