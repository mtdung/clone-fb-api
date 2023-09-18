package vn.edu.fpt.horo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.constant.AdvisorStatus;
import vn.edu.fpt.horo.dto.response.account.InformationAdvisorFollowing;
import vn.edu.fpt.horo.entity.Advisor;

import java.util.List;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, String> {

    Optional<Advisor> findFirstByAccount_AccountId(String accountId);

    Optional<Advisor> findFirstByAccount_AccountIdAndStatus(String accountId, AdvisorStatus status);

    Page<Advisor> findByAccount_AccountIdNot(String accountId, Pageable pageable);

    Page<Advisor> findByAccount_AccountIdNotAndAccount_FullNameContaining(String accountId, String name, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select followers_account_id as accountId from advisors_followers where advisor_advisor_id = :advisorId order by followers_account_id \n#pageable\n")
    Page<AdvisorFollowerId> getAdvisorFollower(String advisorId, Pageable pageable);

    interface AdvisorFollowerId{
        String getAccountId();
    }

}
