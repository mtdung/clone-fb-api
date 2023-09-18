package vn.edu.fpt.horo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.horo.dto.admin.GetListWithDrawResponse;
import vn.edu.fpt.horo.dto.admin.PaymentInCompilationResponse;
import vn.edu.fpt.horo.dto.admin.PaymentOutCompilationResponse;
import vn.edu.fpt.horo.dto.response.payment.CreateWithDrawResponse;
import vn.edu.fpt.horo.dto.request.payment.IncreaseCoinRequest;
import vn.edu.fpt.horo.dto.request.payment.CreateWithDrawRequest;
import vn.edu.fpt.horo.dto.response.payment.TransactionResponse;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    BigDecimal increaseCoin(String accountId, IncreaseCoinRequest request);

    CreateWithDrawResponse withdrawCoin(String accountId, CreateWithDrawRequest request);

    Page<GetListWithDrawResponse> getListWithDraw(Pageable pageable, String search);

    Page<TransactionResponse> getLstTrans(Long transType, Pageable pageable);

    Page<PaymentOutCompilationResponse> getOutCompilation(Pageable pageable, String search);

    List<PaymentOutCompilationResponse> getOutAllCompilation();

    Page<PaymentInCompilationResponse> getInCompilation(Pageable pageable, String search);

    List<PaymentInCompilationResponse> getInAllCompilation();
}
