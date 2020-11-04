package info.noip.darkportal.finance.api.v1.controller;

import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.service.PaymentService;
import info.noip.darkportal.finance.data.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonControllerTest extends AbstractTest {

    public static final Long PERSON_ID = 1L;
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Smith";
    @Mock
    private PersonService personService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private Person testPerson;
    @Mock
    private Payment expense;

    private PersonController personController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);//inject mock objects to this class
        //a PersonController with mocked services
        personController = new PersonController(personService, paymentService, null);
        //a mock MVC with only 1 controller
        mvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception {
        //given that the person service will return 1 person with first and last name
        when(personService.findById(PERSON_ID)).thenReturn(testPerson);
        when(testPerson.getFirstName()).thenReturn(FIRST_NAME);
        when(testPerson.getLastName()).thenReturn(LAST_NAME);

        //we expect to receive a json object with first and last name when calling the API
        mvc.perform(get("/people/" + PERSON_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME));
    }

    @Test
    void getExpenses() throws Exception {
        //given that the person service will return 1 person with an expense
        when(personService.findById(PERSON_ID)).thenReturn(testPerson);
        when(testPerson.getExpenses()).thenReturn(Arrays.asList(expense));
        when(expense.getAmountCents()).thenReturn(-1000L);

        //we expect an array of Payment, each has amount less than 0
        mvc.perform(get("/people/" + PERSON_ID + "/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[]").isArray())
                .andExpect(jsonPath("$[]", hasSize(2)))
                .andExpect(jsonPath("$[0].amountCents").value(lessThan(0L)));
    }

    @Test
    void getIncomes() {
    }

    @Test
    void getPayment() {
    }
}