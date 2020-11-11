package info.noip.darkportal.finance.api.v1.controller;

import info.noip.darkportal.finance.api.v1.dto.PaymentResponseDTO;
import info.noip.darkportal.finance.api.v1.dto.PersonRequestDTO;
import info.noip.darkportal.finance.api.v1.dto.PersonResponseDTO;
import info.noip.darkportal.finance.api.v1.mapper.PaymentMapper;
import info.noip.darkportal.finance.api.v1.mapper.PersonMapper;
import info.noip.darkportal.finance.data.Messages;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.service.PaymentService;
import info.noip.darkportal.finance.data.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This @RestController replace @Controller and @ResponseBody annotation
 * */
@RestController
@RequestMapping("/people")
public class PersonController {
    private PersonService personService;
    private PaymentService paymentService;
    private Messages messages;
    private PersonMapper personMapper;
    private PaymentMapper paymentMapper;

    public PersonController(PersonService personService, PaymentService paymentService, Messages messages, PersonMapper personMapper, PaymentMapper paymentMapper) {
        this.personService = personService;
        this.paymentService = paymentService;
        this.messages = messages;
        this.personMapper = personMapper;
        this.paymentMapper = paymentMapper;
    }

    @GetMapping("/{personId}")
    public PersonResponseDTO getPerson(@PathVariable Long personId) {
        PersonResponseDTO personResponseDTO = personMapper.fromDomain(personService.findById(personId));
        return personResponseDTO;
    }

    @GetMapping("/{personId}/expenses")
    public List<PaymentResponseDTO> getExpenses(@PathVariable Long personId) {
        return personService.findById(personId).getExpenses().stream()
                .map(payment -> paymentMapper.fromDomain(payment))
                .collect(Collectors.toList());
    }

    @GetMapping("/{personId}/incomes")
    public List<PaymentResponseDTO> getIncomes(@PathVariable Long personId) {
        return personService.findById(personId).getIncomes().stream()
                .map(payment -> paymentMapper.fromDomain(payment))
                .collect(Collectors.toList());
    }

    @GetMapping("/{personId}/payments/{paymentId}")
    public PaymentResponseDTO getPayment(@PathVariable Long personId, @PathVariable Long paymentId) {
        Payment payment = paymentService.findById(paymentId);
        if (payment.getPerson().getId() != personId) {
            throw new EntityNotFoundException(messages.get("jpa.entityNotFound"));
        }
        return paymentMapper.fromDomain(payment);
    }

    @PostMapping({"","/"})
    public ResponseEntity<PersonResponseDTO> createPerson(@RequestBody PersonRequestDTO personReq) {
        Person person = personService.save(personMapper.fromDto(personReq));
        ResponseEntity<PersonResponseDTO> resp = new ResponseEntity<>(personMapper.fromDomain(person),
                HttpStatus.CREATED);
        return resp;
    }
}
