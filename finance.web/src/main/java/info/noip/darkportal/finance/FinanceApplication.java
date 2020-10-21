package info.noip.darkportal.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import info.noip.darkportal.finance.data.service.PaymentService;

@SpringBootApplication
public class FinanceApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(FinanceApplication.class, args);
		PaymentService paymentService =  ctx.getBean(PaymentService.class);
		paymentService.findAll().forEach(System.out::println);
	}

}
