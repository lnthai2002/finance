package info.noip.darkportal.finance.web.controller;

import info.noip.darkportal.finance.data.Messages;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.service.PaymentService;
import info.noip.darkportal.finance.data.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private PersonService personService;
    private PaymentService paymentService;
    private Messages messages;

    public PersonController(PersonService personService, PaymentService paymentService, Messages messages) {
        this.personService = personService;
        this.paymentService = paymentService;
        this.messages = messages;
    }

    @GetMapping("/{personId}")
    public Person getPerson(@PathVariable Long personId) {
        return personService.findById(personId);
    }

    @GetMapping("/{personId}/expenses")
    public List<Payment> getExpenses(@PathVariable Long personId) {
        return personService.findById(personId).getExpenses();
    }

    @GetMapping("/{personId}/incomes")
    public List<Payment> getIncomes(@PathVariable Long personId) {
        return personService.findById(personId).getIncomes();
    }

    @GetMapping("/{personId}/payments/{paymentId}")
    public Payment getPayment(@PathVariable Long personId, @PathVariable Long paymentId) {
        Payment payment = paymentService.findById(paymentId);
        if (payment.getPerson().getId() != personId) {
            throw new EntityNotFoundException(messages.get("jpa.entityNotFound"));
        }
        return payment;
    }
}
