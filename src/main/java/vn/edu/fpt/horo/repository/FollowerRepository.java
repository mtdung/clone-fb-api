package vn.edu.fpt.horo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.entity.Followers;

import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Repository
public interface FollowerRepository extends JpaRepository<Followers, String> {
    Followers findFirstByFollowByAndFollowTo(Account followBy, Account followTo);

    int countAllByFollowBy_AccountId(String accountId);

    int countAllByFollowTo_AccountId(String accountId);

    Page<Followers> findAllByFollowBy_AccountId(String accountId, Pageable pageable);

    Page<Followers> findAllByFollowTo_AccountId(String accountId, Pageable pageable);
}
