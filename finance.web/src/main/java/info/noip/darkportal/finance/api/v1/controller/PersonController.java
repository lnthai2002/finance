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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
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
        return personService.findById(personId).expenses().stream()
                .map(payment -> paymentMapper.fromDomain(payment))
                .collect(Collectors.toList());
    }

    @GetMapping("/{personId}/incomes")
    public List<PaymentResponseDTO> getIncomes(@PathVariable Long personId) {
        return personService.findById(personId).incomes().stream()
                .map(payment -> paymentMapper.fromDomain(payment))
                .collect(Collectors.toList());
    }

    @GetMapping("/{personId}/payments/{paymentId}")
    public PaymentResponseDTO getPayment(@PathVariable Long personId, @PathVariable Long paymentId) {
        Payment payment = paymentService.findById(paymentId);
        if (payment.person().id() != personId) {
            throw new EntityNotFoundException(messages.get("jpa.entityNotFound"));
        }
        return paymentMapper.fromDomain(payment);
    }

    @PostMapping({"","/"})
    public ResponseEntity<PersonResponseDTO> createPerson(@RequestBody PersonRequestDTO personReq, UriComponentsBuilder uriComponentsBuilder) {
        Person person = personService.save(personMapper.fromDto(personReq));

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set(HttpHeaders.LOCATION,
                uriComponentsBuilder.path("/people/{personId}").buildAndExpand(person.id()).toUriString());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(responseHeader)
                .body(null);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<PersonResponseDTO> updatePerson(@PathVariable Long personId, @RequestBody PersonRequestDTO personReq) {
        Person person = personMapper.fromDto(personReq);
        person.id(personId);//JPA do an update if the ID exists
        try {
            personService.update(person);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);
        }
        catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PatchMapping("/{personId}")
    public ResponseEntity<PersonResponseDTO> patchPerson(@PathVariable Long personId, @RequestBody PersonRequestDTO personReq) {
        Person person = personMapper.fromDto(personReq);
        person.id(personId);//JPA do an update if the ID exists
        try {
            personService.patch(person);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);
        }
        catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
