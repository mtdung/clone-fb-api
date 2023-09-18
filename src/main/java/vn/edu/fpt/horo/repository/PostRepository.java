package vn.edu.fpt.horo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.dto.response.post.GetPostDetailResponse;
import vn.edu.fpt.horo.dto.response.post.GetPostResponse;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.entity.Poster;

import java.util.List;

/**
 * vn.edu.fpt.accounts.repository
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Repository
public interface PostRepository extends JpaRepository<Poster, String> {

    @Query(value = "select p from Poster p where p.title like %?1%")
    List<Poster> findPostByName(String title);


    Page<Poster> findAllByCreatedBy_AccountId(String accountId, Pageable pageable);

    Page<Poster> findAllByCreatedByIn(List<Account> accounts, Pageable pageable);

    int countAllByCreatedBy_AccountId(String accountId);
}
