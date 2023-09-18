package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.edu.fpt.horo.dto.admin.AllBookingInfoResponse;
import vn.edu.fpt.horo.dto.request.booking.UpdateBookingStatusRequest;
import vn.edu.fpt.horo.dto.response.booking.CreatedBookingResponse;
import vn.edu.fpt.horo.dto.response.booking.GetBookingDetailResponse;
import vn.edu.fpt.horo.entity.Booking;
import vn.edu.fpt.horo.service.FileService;

import java.util.List;

/**
 * vn.edu.fpt.accounts.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(componentModel = "spring", uses = {FileService.class})
public interface BookingMapper {
    GetBookingDetailResponse mapBookingDetail(Booking booking);

    CreatedBookingResponse mapCreateBookingResponse(Booking booking);

    AllBookingInfoResponse mapAllBookingsInfoResponse(Booking booking);

}
