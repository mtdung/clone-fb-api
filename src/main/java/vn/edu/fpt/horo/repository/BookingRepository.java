package vn.edu.fpt.horo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.entity.Booking;

import java.time.LocalDateTime;
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
public interface BookingRepository extends JpaRepository<Booking, String> {

    Optional<Booking> findById(String bookingId);

    Long countAllByStatus(BookingStatus status);

    Integer countAllByStatusAndAdvisor_AdvisorId(BookingStatus status, String advisor);

    Page<Booking> findAllByAdvisor_AdvisorIdAndStatus(String advisorId, BookingStatus status, Pageable pageable);

    Page<Booking> findAllByAdvisor_AdvisorIdAndStatusIn(String advisorId, List<BookingStatus> status, Pageable pageable);

    Page<Booking> findAllByCreatedBy_AccountIdAndStatus(String accountId, BookingStatus status, Pageable pageable);

    Page<Booking> findAllByCreatedBy_AccountIdAndStatusIn(String accountId, List<BookingStatus> status, Pageable pageable);

    List<Booking> findAllByAdvisor_AdvisorIdAndStatusInAndBookingTimeBetween(String advisorId, List<BookingStatus> status, LocalDateTime bookingTimeFrom, LocalDateTime bookingTimeTo);

    @Query(value = "select * from bookings where DATE(booking_time) = CURDATE()" +
            " and slot = :slot and status = :status", nativeQuery = true)
    List<Booking> findAllBookingInDayBySlotAndByStatus(int slot, String status);

    @Query(value = "select * from bookings where DATE(booking_time) = CURDATE() - :day and status = :status", nativeQuery = true)
    List<Booking> findAllBookingInDayByDayBeforeAndByStatus(int day, String status);

    Booking findFirstByAdvisor_AdvisorIdAndSlotAndBookingTimeAndStatusIn(String advisorId, Integer slot, LocalDateTime bookingTime, List<BookingStatus> statuses);

    int countAllByCreatedBy_AccountIdAndStatus(String accountId, BookingStatus status);

    int countAllByCreatedBy_AccountIdAndStatusIn(String accountId, List<BookingStatus> status);

    Page<Booking> findAllByBookingIdContainingOrAdvisor_Account_FullNameContaining(String search, String search2, Pageable pageable);
}
