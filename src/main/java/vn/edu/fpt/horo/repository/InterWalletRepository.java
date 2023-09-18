package vn.edu.fpt.horo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.constant.InterWalletStatus;
import vn.edu.fpt.horo.entity.InterWallet;

import java.util.Optional;

@Repository
public interface InterWalletRepository extends JpaRepository<InterWallet, String> {
    InterWallet findByTransactionId(String transactionId);
    Optional<InterWallet> findByTransactionIdAndStatus(String transactionId, InterWalletStatus status);
}
