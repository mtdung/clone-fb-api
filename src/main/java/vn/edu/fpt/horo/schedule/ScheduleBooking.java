package vn.edu.fpt.horo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.edu.fpt.horo.service.BookingService;

@Component
@Slf4j
public class ScheduleBooking {

    @Autowired
    BookingService bookingService;

    @Scheduled(fixedRate = 60 * 1000)
    public void scheduleCancelBookingIfOutTimeAccept() {
        // call send email method here
        log.info("Start scheduleCancelBookingIfOutTimeAccept");
        bookingService.cancelBookingIfOutTimeAccept();
        log.info("Stop scheduleCancelBookingIfOutTimeAccept");
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void scheduleHanldeBookingIfNotHaveResponse() {
        // call send email method here
        log.info("Start scheduleHanldeBookingIfNotHaveResponse");
        bookingService.hanldeBookingIfNotHaveResponse();
        log.info("Stop scheduleHanldeBookingIfNotHaveResponse");
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Ho_Chi_Minh")
    public void scheduleFinishBookingWaiting() {
        // call send email method here
        log.info("Start scheduleFinishBookingWaiting");
        bookingService.finishBookingWaiting();
        log.info("Stop scheduleFinishBookingWaiting");
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void scheduleHanldelAdvisorAbsent() {
        // call send email method here
        log.info("Start scheduleHanldelAdvisorAbsent");
        bookingService.handleAdvisorAbsent();
        log.info("Stop scheduleHanldelAdvisorAbsent");
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void scheduleNotiBeforeBooking() {
        // call send email method here
        log.info("Start scheduleHanldelAdvisorAbsent");
        bookingService.notiBeforeBooking();
        log.info("Stop scheduleHanldelAdvisorAbsent");
    }
}
