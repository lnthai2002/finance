package info.noip.darkportal.finance.api.v1.mapper;

import info.noip.darkportal.finance.api.v1.dto.PaymentRequestDTO;
import info.noip.darkportal.finance.api.v1.dto.PaymentResponseDTO;
import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.model.Payment;
import info.noip.darkportal.finance.data.model.PaymentType;
import info.noip.darkportal.finance.data.model.Person;
import info.noip.darkportal.finance.data.service.CategoryService;
import info.noip.darkportal.finance.data.service.PaymentTypeService;
import info.noip.darkportal.finance.data.service.PersonService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PaymentMapper {
    private final CategoryService categoryService;
    private final PaymentTypeService paymentTypeService;
    private final PersonService personService;

    public PaymentMapper(CategoryService categoryService, PaymentTypeService paymentTypeService, PersonService personService) {
        this.categoryService = categoryService;
        this.paymentTypeService = paymentTypeService;
        this.personService = personService;
    }

    public PaymentResponseDTO fromDomain(Payment payment) {
        return new PaymentResponseDTO(payment.transactionDate(),
                payment.amountCents(),
                payment.paymentType().name(),
                payment.category().name(),
                payment.person().id()
        );
    }

    public Payment fromDto(PaymentRequestDTO dto) {
        //TODO: this is expensive and redundant because the ID is stored in Payment in the end
        Category category = categoryService.findById(dto.getCategoryId());
        PaymentType paymentType = paymentTypeService.findById(dto.getPaymentTypeId());
        Person person = personService.findById(dto.getOwnerId());
        //TODO: make a global date format
        DateTimeFormatter dateFm = DateTimeFormatter.ofPattern("yyyy-MM-d");
        Payment payment = new Payment(category, dto.getAmountCents())
                .transactionDate(LocalDate.parse(dto.getTransactionDate(), dateFm))
                .person(person)
                .paymentType(paymentType);
        return payment;
    }
}
