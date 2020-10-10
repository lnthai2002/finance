package info.noip.darkportal.finance.repository;

import info.noip.darkportal.finance.model.Payment;

import java.util.Set;

public interface PaymentRepository extends CrudRepository<Payment> {
    Set<Payment> findAllExpenses();
    Set<Payment> findAllIncomes();
}
