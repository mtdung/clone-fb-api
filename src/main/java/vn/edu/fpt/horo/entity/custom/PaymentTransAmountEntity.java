package vn.edu.fpt.horo.entity.custom;

import java.math.BigDecimal;

public interface PaymentTransAmountEntity {
    BigDecimal getTransAmount();
    BigDecimal getCoinTransAmount();
    BigDecimal getAdminCollectAmount();
}
