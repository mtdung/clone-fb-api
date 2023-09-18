package vn.edu.fpt.horo.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DashBoardResponse {
    BigDecimal totalMoneyTrans;
    BigDecimal totalWithdraw;
    BigDecimal totalItemTrans;
    BigDecimal totalItemWithDraw;
    BigDecimal adminCollect;
    Long countAdvisor;
    Long countOfUser;
    Long countAdvisorRequest;
    Long countCancelRequest;
    Long countPost;
    Long pendingBooking;
    Long cancelBooking;
    Long successBooking;
}
