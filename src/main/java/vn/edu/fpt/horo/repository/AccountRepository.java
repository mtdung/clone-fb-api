package vn.edu.fpt.horo.repository;

/**
 * vn.edu.fpt.accounts.repository
 **/

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Page<Account> findAllByAccountIdContainingOrFullNameContainingOrProfile_PhoneNumberContaining(String search, String search1, String search2, Pageable pageable);

    @Query("select ac from Account ac where ac.username like ?1  or ac.email like ?1 ")
    Optional<Account> findAccountByEmailOrUsername(String emailOrUsername);

    Optional<Account> findAccountByAccountId(String accountId);

    Optional<Account> findAccountByEmail(String email);
    Optional<Account> findAccountByUsername(String username);

    @Query(value = "select ac from Account ac where ac.username like %?1%")
    List<Account> findAccountByName(String fullName);

    @Query(nativeQuery = true,
    value = "select following_account_id as accountId from accounts_following where account_account_id = :accountId order by following_account_id \n#pageable\n"
            , countQuery = "select count(*) from accounts_following where account_account_id = :accountId order by following_account_id ")
    Page<AccountFollowingId> getAccountFollowing(String accountId, Pageable pageable);

    interface AccountFollowingId{
        String getAccountId();
    }

}

