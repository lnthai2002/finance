package info.noip.darkportal.finance.data.repository;

import info.noip.darkportal.finance.data.model.Payment;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment> {
    List<Payment> findAll();
    List<Payment> findAllExpenses();
    List<Payment> findAllIncomes();
}
