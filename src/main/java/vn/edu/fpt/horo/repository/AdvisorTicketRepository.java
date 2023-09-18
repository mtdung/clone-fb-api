package vn.edu.fpt.horo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.AdvisorTicket;

import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Repository
public interface AdvisorTicketRepository extends JpaRepository<AdvisorTicket, String> {
    AdvisorTicket findFirstByTicketId(String ticketId);

    List<AdvisorTicket> findAllByAdvisorIsNotNull();

    List<AdvisorTicket> findAllByAdvisorIsNotNullAndCreatedBy_FullNameContaining(String search);

    Long countAllByAdvisorIsNull();

    Long countAllByAdvisorIsNotNull();
}
