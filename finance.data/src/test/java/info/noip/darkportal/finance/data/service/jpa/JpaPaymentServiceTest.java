package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.Messages;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class JpaPaymentServiceTest {

    @Mock
    private Payment expense;
    @Mock
    private Payment income;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private Messages messages;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        //an expense is a payment
        when(expense.amountCents()).thenReturn(-10000L);//and has negative amount

        //an income is a payment
        when(income.amountCents()).thenReturn(20000L);//and has positive amount

        //a payment repository is one can return:
        when(paymentRepository.findExpenses(anyLong())).thenReturn(Arrays.asList(new Payment[] {expense}));//some expenses
        when(paymentRepository.findIncomes(anyLong())).thenReturn(Arrays.asList(new Payment[] {income}));//some incomes
    }

    @Test
    void findAllExpenses() {
        //given
        JpaPaymentService service = new JpaPaymentService(paymentRepository, messages);

        //act
        //this method just call the repository to do the work. no business logic to test
        List<Payment> expenses = service.findAllExpenses(1L);

        //validate
        assertNotNull(expenses);//it should return an list that is not null
    }

    @Test
    void findAllIncomes() {
        //given
        JpaPaymentService service = new JpaPaymentService(paymentRepository, messages);

        //act
        //this method just call the repository to do the work. no business logic to test
        List<Payment> incomes = service.findAllIncomes(1L);

        //validate
        assertNotNull(incomes);//it should return an list that is not null
    }
}