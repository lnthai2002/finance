package info.noip.darkportal.finance.api.v1.controller;

import info.noip.darkportal.finance.data.Messages;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a standard controller, thus to return POJO as Json, i need to annotate response with @ResponseBody
 * The @ResController annotation can be used to replace both @Controller and @ResponseBody*/
@RequestMapping("/payments")
@Controller
public class PaymentController {
    private PaymentService paymentService;
    private Messages messages;

    public PaymentController(PaymentService paymentService, Messages messages) {
        this.paymentService = paymentService;
        this.messages = messages;
    }

    @GetMapping({"","/"})
    public @ResponseBody List<Payment> getPayments(@RequestParam Long personId) {
        List<Payment> payments = paymentService.findAll(personId);
        return payments;
    }

    @GetMapping("/{id}")
    public @ResponseBody Payment getPayment(@PathVariable Long id, @RequestParam Long personId) {
        Payment payment = paymentService.findById(id);
        if (payment.person().id() != personId) {
            throw new EntityNotFoundException(messages.get("jpa.entityNotFound"));
        }
        return payment;
    }
}
