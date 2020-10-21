package info.noip.darkportal.finance.data.repository;

import info.noip.darkportal.finance.data.model.PaymentType;

public interface PaymentTypeRepository extends BaseRepository<PaymentType, Long> {
    @Override
    Iterable<PaymentType> findAll();
}
