package vn.edu.fpt.horo.constant;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
public enum BookingStatus {

    WAITING_FOR_APPROVAL,// đang chờ thầy đồng ý
    WAITING_FOR_COMPLETED,// đang chờ user ấn kết thúc để thanh toans cho thầy
    APPROVED,// thầy đã đồng ý
    PROCESSING,// đang trong quá trình xem tử vi
    CANCELLED,// đã hủy từ phía user
    COMPLETED,// đã hoàn thành, đã thanh toán cho thầy
    REJECTED,// đã hủy từ phía người thầy
    TIMEOUT,// đã quá thời gian nhưng thầy chưa đồng ý
    ADVISOR_ABSENT // advisor muộn 10p chưa join book
}
