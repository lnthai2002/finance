package info.noip.darkportal.finance.data.repository;

import info.noip.darkportal.finance.data.model.Payment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends BaseRepository<Payment, Long> {
    Iterable<Payment> findAllByPersonId(Long personId);

    @Override
    Iterable<Payment> findAll(Sort sort);

    Iterable<Payment> findByCategoryEffect(Integer direction);

    @Query("select p from Payment p, Category c where p.person.id = ?1 and c.effect = -1")
    Iterable<Payment> findExpenses(Long personId);

    @Query("select p from Payment p, Category c where p.person.id = ?1 and c.effect = 1")
    Iterable<Payment> findIncomes(Long personId);
}
