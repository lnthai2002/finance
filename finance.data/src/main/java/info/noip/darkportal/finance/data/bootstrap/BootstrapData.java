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
        PaymentType creditCard = new PaymentType().name("Credit Card");
        PaymentType paypal = new PaymentType().name("Paypal");
        PaymentType debitCard = new PaymentType().name("Debit Card");
        paymentTypeRepository.save(creditCard);
        paymentTypeRepository.save(paypal);
        paymentTypeRepository.save(debitCard);

        //some categories
        Category housing = new Category().name("Housing").effect(-1);
        Category electricity = new Category().name("Electricity").effect(-1);
        Category water = new Category().name("Water tax").effect(-1);
        Category restaurant = new Category().name("Restaurant").effect(-1);
        Category grocery = new Category().name("Grocery").effect(-1);
        Category salary = new Category().name("Salary").effect(1);
        categoryService.save(housing);
        categoryService.save(electricity);
        categoryService.save(water);
        categoryService.save(restaurant);
        categoryService.save(grocery);
        categoryService.save(salary);

        //some people
        Person john = new Person()
            .firstName("John")
            .lastName("Doe")
            .incomes(null)
            .expenses(null);
        Person jane = new Person()
            .firstName("Jane")
            .lastName("Catherine")
            .incomes(null)
            .expenses(null);
        personRepository.save(john);
        personRepository.save(jane);

        //some payment
        DateTimeFormatter dateFm = DateTimeFormatter.ofPattern("yyyy-MM-d");
        Payment p1 = new Payment()
        .transactionDate(LocalDate.parse("2020-01-10", dateFm))
        .category(housing)
        .amountCents(100000L)
        .paymentType(debitCard)
        .person(jane);

        Payment p2 = new Payment()
        .transactionDate(LocalDate.parse("2020-01-11", dateFm))
        .category(grocery)
        .amountCents(20000L)
        .paymentType(creditCard)
        .person(john);

        Payment p3 = new Payment()
        .transactionDate(LocalDate.parse("2020-01-12", dateFm))
        .category(electricity)
        .amountCents(10000L)
        .paymentType(paypal)
        .person(jane);

        Payment p4 = new Payment()
        .transactionDate(LocalDate.parse("2020-01-13", dateFm))
        .category(salary)
        .amountCents(150000L)
        .paymentType(debitCard)
        .person(jane);

        paymentRepository.save(p1);
        paymentRepository.save(p2);
        paymentRepository.save(p3);
        paymentRepository.save(p4);
        System.out.println("Finished loading sample data");
        System.out.println("payment repo now has " + paymentRepository.findAll().size());
    }
}
