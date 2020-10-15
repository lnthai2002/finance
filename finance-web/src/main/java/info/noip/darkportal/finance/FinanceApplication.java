package info.noip.darkportal.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import info.noip.darkportal.finance.data.repository.PaymentRepository;

@SpringBootApplication
public class FinanceApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(FinanceApplication.class, args);
		PaymentRepository paymentRepository =  ctx.getBean(PaymentRepository.class);
		paymentRepository.findAll().forEach(System.out::println);
	}

}
