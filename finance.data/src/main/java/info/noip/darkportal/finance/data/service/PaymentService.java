package info.noip.darkportal.finance.data.service;

import info.noip.darkportal.finance.data.model.Payment;

import java.util.List;

public interface PaymentService extends CrudService<Payment> {
    List<Payment> findAll();
    List<Payment> findAllExpenses();
    List<Payment> findAllIncomes();
}
