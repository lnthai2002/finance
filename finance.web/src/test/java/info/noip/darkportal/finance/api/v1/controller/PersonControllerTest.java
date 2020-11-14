package info.noip.darkportal.finance.api.v1.controller;

import info.noip.darkportal.finance.api.v1.dto.PersonRequestDTO;
import info.noip.darkportal.finance.api.v1.mapper.PaymentMapper;
import info.noip.darkportal.finance.api.v1.mapper.PersonMapper;
import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.service.PaymentService;
import info.noip.darkportal.finance.data.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Captor
    ArgumentCaptor<Person> personArgumentCaptor;

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
        Person testPerson = new Person()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME);
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
        Person testPerson = new Person();
        testPerson.id(PERSON_ID);
        Payment expense = new Payment(
                    new Category().name("Grocery").effect(-1),
                    10000L
                )
                .transactionDate(LocalDate.now())
                .paymentType(
                        new PaymentType()
                                .name("Paypal")
                )
                .person(testPerson);
        testPerson.expenses(Arrays.asList(expense));
        //given that the person service will return 1 person with an expense
        when(personService.findById(PERSON_ID)).thenReturn(testPerson);

        //we expect an array of Payment, each has amount less than 0
        mvc.perform(get("/people/" + PERSON_ID + "/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray())
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[0].amountCents").value(lessThan(0)))
                .andExpect(jsonPath("$[0].ownerId").value(PERSON_ID));;
    }

    @Test
    void shouldReturnAllIncomesForAPerson() throws Exception {
        Person testPerson = new Person();
        testPerson.id(PERSON_ID);
        Payment income = new Payment(
                    new Category().name("Salary").effect(1),
                    200000L
                )
                .transactionDate(LocalDate.now())
                .paymentType(
                        new PaymentType()
                                .name("Cash")
                )
                .person(testPerson);
        testPerson.incomes(Arrays.asList(income));
        //given that the person service will return 1 person with an income
        when(personService.findById(PERSON_ID)).thenReturn(testPerson);

        //we expect an array of Payment, each has amount more than 0
        mvc.perform(get("/people/" + PERSON_ID + "/incomes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]").isArray())
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[0].amountCents").value(greaterThan(0)))
                .andExpect(jsonPath("$[0].ownerId").value(PERSON_ID));;
    }

    @Test
    void shouldReturnAPaymentBelongsToThePersonAsked() throws Exception {
        //given that we have a person with an ID
        Person testPerson = new Person();
        testPerson.id(PERSON_ID);
        //and a payment belongs to such a person
        Payment aPayment = new Payment(new Category().effect(1), 2000L)
                .paymentType(
                        new PaymentType()
                                .name("Cash")
                )
                .person(testPerson);
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
        Person createdPerson = new Person();
        createdPerson.id(PERSON_ID);
        //and we trust the service to save the object correctly so that it can return the object
        when(personService.save(ArgumentMatchers.any(Person.class))).thenReturn(createdPerson);

        //act
        mvc.perform(post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDTO)))
            .andExpect(status().isCreated())
            .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    void updatePerson() throws Exception {
        //given a request
        PersonRequestDTO requestDTO = new PersonRequestDTO(FIRST_NAME, LAST_NAME);
        //and the updated person
        Person updatedPerson = new Person()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .id(PERSON_ID);
        //and assuming the person service is correctly implemented
        when(personService.save(ArgumentMatchers.any(Person.class))).thenReturn(updatedPerson);

        //act
        mvc.perform(put("/people/" + PERSON_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDTO)))
            .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404WhenPatchANonExistedPerson() throws Exception {
        //given a request
        PersonRequestDTO requestDTO = new PersonRequestDTO(FIRST_NAME, LAST_NAME);
        //and assuming the person service blowing up because the person does not exist
        doThrow(new EntityNotFoundException("")).when(personService).patch(personArgumentCaptor.capture());

        //act
        mvc.perform(patch("/people/" + PERSON_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDTO)))
                .andExpect(status().isNotFound());
        Person subjectToPatch = personArgumentCaptor.getValue();
        assertEquals(subjectToPatch.firstName(), FIRST_NAME);
        assertEquals(subjectToPatch.lastName(), LAST_NAME);
    }

    @Test
    void shouldReturn204WhenPatchAnExistedPerson() throws Exception {
        //given a request
        PersonRequestDTO requestDTO = new PersonRequestDTO(FIRST_NAME, LAST_NAME);
        //act
        mvc.perform(patch("/people/" + PERSON_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDTO)))
                .andExpect(status().isNoContent());
        //validate
        verify(personService, times(1)).patch(personArgumentCaptor.capture());
        Person subjectToPatch = personArgumentCaptor.getValue();
        assertEquals(subjectToPatch.firstName(), FIRST_NAME);
        assertEquals(subjectToPatch.lastName(), LAST_NAME);
    }
}