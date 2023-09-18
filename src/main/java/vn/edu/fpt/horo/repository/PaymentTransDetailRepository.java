package vn.edu.fpt.horo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.PaymentTransDetail;
import vn.edu.fpt.horo.entity.custom.PaymentTransAmountEntity;

import java.util.List;

@Repository
public interface PaymentTransDetailRepository extends JpaRepository<PaymentTransDetail, String> {
    @Query(value = "select SUM(coin_trans_amount) as coinTransAmount, SUM(trans_amount) as transAmount, SUM(admin_collect_amount) as adminCollectAmount from payment_trans_detail where trans_type = :transType", nativeQuery = true)
    PaymentTransAmountEntity getTotalTransByTransType(Long transType);

    Page<PaymentTransDetail> findAllByTransTypeAndUserBankNameContaining(Long transType, String search, Pageable pageable);

    PaymentTransDetail findFirstByIdAndErrorCodeIsNull(String id);

    Page<PaymentTransDetail> findAllByTransTypeAndAccountPayment_AccountId(Long transType, String accountId, Pageable pageable);

    Page<PaymentTransDetail> findAllByTransTypeAndAccountPayment_FullNameContaining(Long transType, String fullName, Pageable pageable);

    List<PaymentTransDetail> findAllByTransType(Long transType);
}
