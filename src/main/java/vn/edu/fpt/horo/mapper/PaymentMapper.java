package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.edu.fpt.horo.dto.admin.GetListWithDrawResponse;
import vn.edu.fpt.horo.dto.admin.PaymentInCompilationResponse;
import vn.edu.fpt.horo.dto.admin.PaymentOutCompilationResponse;
import vn.edu.fpt.horo.dto.response.payment.TransactionResponse;
import vn.edu.fpt.horo.entity.PaymentTransDetail;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    GetListWithDrawResponse getWithDrawMapper(PaymentTransDetail paymentTransDetail);

    TransactionResponse getTransMapper(PaymentTransDetail paymentTransDetail);

    @Mapping(source = "accountPayment.fullName", target = "fullName")
    @Mapping(source = "accountPayment.username", target = "username")
    @Mapping(source = "accountPayment.accountId", target = "accountId")
    PaymentInCompilationResponse getInCompilation(PaymentTransDetail paymentTransDetail);

    @Mapping(source = "accountPayment.fullName", target = "fullName")
    @Mapping(source = "accountPayment.username", target = "username")
    @Mapping(source = "accountPayment.accountId", target = "accountId")
    PaymentOutCompilationResponse getOutCompilation(PaymentTransDetail paymentTransDetail);
}
