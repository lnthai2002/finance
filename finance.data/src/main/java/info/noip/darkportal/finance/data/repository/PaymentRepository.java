package info.noip.darkportal.finance.data.repository;

import info.noip.darkportal.finance.data.model.Payment;

import java.util.Set;

public interface PaymentRepository extends CrudRepository<Payment> {
    Set<Payment> findAllExpenses();
    Set<Payment> findAllIncomes();
}
