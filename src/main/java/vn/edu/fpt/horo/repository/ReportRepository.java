package vn.edu.fpt.horo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.Report;

/**
 * vn.edu.fpt.horo.repository
 *
 * @author : Portgas.D.Ace
 * @created : 02/05/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Repository
public interface ReportRepository extends JpaRepository<Report, String> {
    Page<Report> findAllByCreatedBy_FullNameContaining(String search, Pageable pageable);

    Report findByReportId(String reportId);

    @Query(value = "update reports set status = true where report_id = :reportId", nativeQuery = true)
    void updateReportsStatus(String reportId);
}
