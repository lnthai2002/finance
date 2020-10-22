package info.noip.darkportal.finance.web.controller;

import info.noip.darkportal.finance.data.Messages;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/payments")
@RestController
public class PaymentController {
    private PaymentService paymentService;
    private Messages messages;

    public PaymentController(PaymentService paymentService, Messages messages) {
        this.paymentService = paymentService;
        this.messages = messages;
    }

    @GetMapping({"","/"})
    public List<Payment> getPayments(@RequestParam Long personId) {
        List<Payment> payments = paymentService.findAll(personId);
        return payments;
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id, @RequestParam Long personId) {
        Payment payment = paymentService.findById(id);
        if (payment.getPerson().getId() != personId) {
            throw new EntityNotFoundException(messages.get("jpa.entityNotFound"));
        }
        return payment;
    }
}
