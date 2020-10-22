package info.noip.darkportal.finance.web.controller;

import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/payments")
@RestController
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping({"","/"})
    public List<Payment> getPayments(@RequestParam Long personId) {
        List<Payment> payments = paymentService.findAll(personId);
        return payments;
    }
}
