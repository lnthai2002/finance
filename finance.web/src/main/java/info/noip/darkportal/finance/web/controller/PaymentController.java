package info.noip.darkportal.finance.web.controller;

import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.service.PaymentRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/payments")
@RestController
public class PaymentController {
    private PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping({"","/"})
    public List<Payment> getPayments(Model model) {
        List<Payment> payments = paymentRepository.findAll();
        return payments;
    }
}
