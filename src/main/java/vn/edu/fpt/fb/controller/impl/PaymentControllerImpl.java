package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.response.payment.CreateWithDrawResponse;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.dto.request.payment.IncreaseCoinRequest;
import vn.edu.fpt.fb.dto.request.payment.CreateWithDrawRequest;
import vn.edu.fpt.fb.dto.response.payment.TransactionResponse;
import vn.edu.fpt.fb.factory.ResponseFactory;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PaymentControllerImpl implements PaymentController {
    private final ResponseFactory responseFactory;
    private final PaymentService paymentService;
    @Override
    public ResponseEntity<GeneralResponse<Object>> increaseCoin(String accountId, IncreaseCoinRequest request) {
        return responseFactory.response(paymentService.increaseCoin(accountId, request));
    }

    @Override
    public ResponseEntity<GeneralResponse<CreateWithDrawResponse>> requestWithDraw(String accountId, CreateWithDrawRequest request) {
        return responseFactory.response(paymentService.withdrawCoin(accountId, request));
    }

    @Override
    public ResponseEntity<GeneralResponse<Page<TransactionResponse>>> lstTrans(Long transType, Pageable pageable) {
        return responseFactory.response(paymentService.getLstTrans(transType, pageable));
    }
}
