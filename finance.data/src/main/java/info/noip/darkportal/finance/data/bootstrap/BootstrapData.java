package info.noip.darkportal.finance.data.bootstrap;

import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.model.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import info.noip.darkportal.finance.data.service.CategoryService;
import info.noip.darkportal.finance.data.service.PaymentService;
import info.noip.darkportal.finance.data.service.PaymentTypeService;
import info.noip.darkportal.finance.data.service.PersonService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
//@Profile({"mapdata","default"})
public class BootstrapData implements CommandLineRunner {

    private PaymentTypeService paymentTypeRepository;
    private CategoryService categoryService;
    private PersonService personRepository;
    private PaymentService paymentRepository;

    public BootstrapData(PaymentTypeService paymentTypeRepository, CategoryService categoryService, PersonService personRepository, PaymentService paymentRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.categoryService = categoryService;
        this.personRepository = personRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Begin to load sample data");
        //some payment type
        PaymentType creditCard = new PaymentType("Credit Card");
        PaymentType paypal = new PaymentType("Paypal");
        PaymentType debitCard = new PaymentType("Debit Card");
        paymentTypeRepository.save(creditCard);
        paymentTypeRepository.save(paypal);
        paymentTypeRepository.save(debitCard);

        //some categories
        Category housing = new Category("Housing", -1);
        Category electricity = new Category("Electricity", -1);
        Category water = new Category("Water tax", -1);
        Category restaurant = new Category("Restaurant", -1);
        Category grocery = new Category("Grocery", -1);
        Category salary = new Category("Salary", 1);
        categoryService.save(housing);
        categoryService.save(electricity);
        categoryService.save(water);
        categoryService.save(restaurant);
        categoryService.save(grocery);
        categoryService.save(salary);

        //some people
        Person john = Person.Builder.aPerson()
            .withFirstName("John")
            .withLastName("Doe")
            .withIncomes(null)
            .withExpenses(null)
            .build();
        Person jane = Person.Builder.aPerson()
            .withFirstName("Jane")
            .withLastName("Catherine")
            .withIncomes(null)
            .withExpenses(null)
            .build();
        personRepository.save(john);
        personRepository.save(jane);

        //some payment
        DateTimeFormatter dateFm = DateTimeFormatter.ofPattern("yyyy-MM-d");
        Payment p1 = Payment.Builder.aPayment()
        .withTransactionDate(LocalDate.parse("2020-01-10", dateFm))
        .withCategory(housing)
        .withAmountCents(100000L)
        .withPaymentType(debitCard)
        .withPerson(jane)
        .build();

        Payment p2 = Payment.Builder.aPayment()
        .withTransactionDate(LocalDate.parse("2020-01-11", dateFm))
        .withCategory(grocery)
        .withAmountCents(20000L)
        .withPaymentType(creditCard)
        .withPerson(john)
        .build();

        Payment p3 = Payment.Builder.aPayment()
        .withTransactionDate(LocalDate.parse("2020-01-12", dateFm))
        .withCategory(electricity)
        .withAmountCents(10000L)
        .withPaymentType(paypal)
        .withPerson(jane)
        .build();

        Payment p4 = Payment.Builder.aPayment()
        .withTransactionDate(LocalDate.parse("2020-01-13", dateFm))
        .withCategory(salary)
        .withAmountCents(150000L)
        .withPaymentType(debitCard)
        .withPerson(jane)
        .build();

        paymentRepository.save(p1);
        paymentRepository.save(p2);
        paymentRepository.save(p3);
        paymentRepository.save(p4);
        System.out.println("Finished loading sample data");
        System.out.println("payment repo now has " + paymentRepository.findAll().size());
    }
}
