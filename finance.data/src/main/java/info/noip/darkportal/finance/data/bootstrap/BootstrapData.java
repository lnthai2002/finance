package info.noip.darkportal.finance.data.bootstrap;

import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.model.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import info.noip.darkportal.finance.data.service.CategoryRepository;
import info.noip.darkportal.finance.data.service.PaymentRepository;
import info.noip.darkportal.finance.data.service.PaymentTypeRepository;
import info.noip.darkportal.finance.data.service.PersonRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Profile({"mapdata","default"})
public class BootstrapData implements CommandLineRunner {

    private PaymentTypeRepository paymentTypeRepository;
    private CategoryRepository categoryRepository;
    private PersonRepository personRepository;
    private PaymentRepository paymentRepository;

    public BootstrapData(PaymentTypeRepository paymentTypeRepository, CategoryRepository categoryRepository, PersonRepository personRepository, PaymentRepository paymentRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.categoryRepository = categoryRepository;
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
        Category housing = new Category("Housing");
        housing.setEffect(-1);
        Category electricity = new Category("Electricity");
        electricity.setEffect(-1);
        Category water = new Category("Water tax");
        water.setEffect(-1);
        Category restaurant = new Category("Restaurant");
        restaurant.setEffect(-1);
        Category grocery = new Category("Grocery");
        grocery.setEffect(-1);
        Category salary = new Category("Salary");
        salary.setEffect(1);
        categoryRepository.save(housing);
        categoryRepository.save(electricity);
        categoryRepository.save(water);
        categoryRepository.save(restaurant);
        categoryRepository.save(grocery);
        categoryRepository.save(salary);

        //some people
        Person john = new Person("John", "Doe");
        Person jane = new Person("Jane", "Catherine");
        personRepository.save(john);
        personRepository.save(jane);

        //some payment
        DateTimeFormatter dateFm = DateTimeFormatter.ofPattern("yyyy-MM-d");
        Payment p1 = new Payment();
        p1.setTransactionDate(LocalDate.parse("2020-01-10", dateFm));
        p1.setCategory(housing);
        p1.setAmountCents(100000L);
        p1.setPaymentType(debitCard);
        p1.setPerson(jane);

        Payment p2 = new Payment();
        p2.setTransactionDate(LocalDate.parse("2020-01-11", dateFm));
        p2.setCategory(grocery);
        p2.setAmountCents(20000L);
        p2.setPaymentType(creditCard);
        p2.setPerson(john);

        Payment p3 = new Payment();
        p3.setTransactionDate(LocalDate.parse("2020-01-12", dateFm));
        p3.setCategory(electricity);
        p3.setAmountCents(10000L);
        p3.setPaymentType(paypal);
        p3.setPerson(jane);

        Payment p4 = new Payment();
        p4.setTransactionDate(LocalDate.parse("2020-01-13", dateFm));
        p4.setCategory(salary);
        p4.setAmountCents(150000L);
        p4.setPaymentType(debitCard);
        p4.setPerson(jane);

        paymentRepository.save(p1);
        paymentRepository.save(p2);
        paymentRepository.save(p3);
        paymentRepository.save(p4);
        System.out.println("Finished loading sample data");
        System.out.println("payment repo now has " + paymentRepository.findAll().size());
    }
}
