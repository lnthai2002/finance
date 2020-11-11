package info.noip.darkportal.finance.api.v1.controller;

import info.noip.darkportal.finance.api.v1.dto.PersonRequestDTO;
import info.noip.darkportal.finance.api.v1.mapper.PaymentMapper;
import info.noip.darkportal.finance.api.v1.mapper.PersonMapper;
import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.service.PaymentService;
import info.noip.darkportal.finance.data.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonControllerTest extends AbstractTest {

    public static final Long PERSON_ID = 1L;
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Smith";
    private static final Long PAYMENT_ID = 1L;
    @Mock
    private PersonService personService;
    @Mock
    private PaymentService paymentService;

    private PersonController personController;

    @BeforeEach
    public void before() {
        PersonMapper personMapper = new PersonMapper();
        PaymentMapper paymentMapper = new PaymentMapper();
        MockitoAnnotations.initMocks(this);//inject mock objects to this class
        //a PersonController with mocked services
        personController = new PersonController(personService, paymentService, null, personMapper, paymentMapper);
        //a mock MVC with only 1 controller
        mvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void shouldReturnAPerson() throws Exception {
        //if we mock a person, when Spring builds a response body it will fail because json serializer cant serialize the mock object
        Person testPerson = Person.Builder.aPerson()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .build();
        //given that the person service will return 1 person with first and last name
        when(personService.findById(PERSON_ID)).thenReturn(testPerson);

        //we expect to receive a json object with first and last name when calling the API
        mvc.perform(get("/people/" + PERSON_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME));
    }

    @Test
    void shouldReturnAllExpensesForAPerson() throws Exception {
        Person testPerson = Person.Builder.aPerson()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withExpenses(
                        Arrays.asList(
                                Payment.Builder.aPayment()
                                        .withTransactionDate(LocalDate.now())
                                        .withAmountCents(10000L)
                                        .withPaymentType(
                                                PaymentType.Builder.aPaymentType()
                                                        .withName("Paypal")
                                                        .build()
                                        )
                                        .withCategory(
                                                Category.Builder.aCategory()
                                                        .withName("Grocery")
                                                        .withEffect(-1)
                                                        .build()
                                        )
                                        .build()
                        )
                )
                .build();
        //given that the person service will return 1 person with an expense
        when(personService.findById(PERSON_ID)).thenReturn(testPerson);

        //we expect an array of Payment, each has amount less than 0
        mvc.perform(get("/people/" + PERSON_ID + "/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray())
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[0].amountCents").value(lessThan(0)));
    }

    @Test
    void shouldReturnAllIncomesForAPerson() throws Exception {
        Person testPerson = Person.Builder.aPerson()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withIncomes(
                        Arrays.asList(
                                Payment.Builder.aPayment()
                                        .withTransactionDate(LocalDate.now())
                                        .withAmountCents(200000L)
                                        .withPaymentType(
                                                PaymentType.Builder.aPaymentType()
                                                        .withName("Cash")
                                                        .build()
                                        )
                                        .withCategory(
                                                Category.Builder.aCategory()
                                                        .withName("Salary")
                                                        .withEffect(1)
                                                        .build()
                                        )
                                        .build()
                        )
                )
                .build();
        //given that the person service will return 1 person with an income
        when(personService.findById(PERSON_ID)).thenReturn(testPerson);

        //we expect an array of Payment, each has amount more than 0
        mvc.perform(get("/people/" + PERSON_ID + "/incomes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray())
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[0].amountCents").value(greaterThan(0)));
    }

    @Test
    void shouldReturnAPaymentBelongsToThePersonAsked() throws Exception {
        //given that we have a person with an ID
        Person testPerson = Person.Builder.aPerson().build();
        testPerson.setId(PERSON_ID);
        //and a payment belongs to such a person
        Payment aPayment = Payment.Builder.aPayment()
                .withCategory(Category.Builder.aCategory().withEffect(1).build())
                .withPaymentType(
                        PaymentType.Builder.aPaymentType()
                                .withName("Cash")
                                .build()
                )
                .withAmountCents(2000L)
                .withPerson(testPerson)
                .build();
        //and the payment service return the payment above
        when(paymentService.findById(PAYMENT_ID)).thenReturn(aPayment);

        //we expect a Payment of the person
        mvc.perform(get("/people/" + PERSON_ID + "/payments/" + PAYMENT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.amountCents").isNumber())
                .andExpect(jsonPath("$.ownerId").value(PERSON_ID));
    }

    @Test
    void createPerson() throws Exception {
        //given a request
        PersonRequestDTO requestDTO = new PersonRequestDTO(FIRST_NAME, LAST_NAME);
        //and an created object
        Person createdPerson = Person.Builder.aPerson()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .build();
        createdPerson.setId(PERSON_ID);
        //and we trust the service to save the object correctly so that it can return the object
        when(personService.save(ArgumentMatchers.any(Person.class))).thenReturn(createdPerson);

        //act
        mvc.perform(post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDTO)))
            .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME));
    }
}