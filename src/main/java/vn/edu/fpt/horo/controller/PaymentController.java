package vn.edu.fpt.horo.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.response.payment.CreateWithDrawResponse;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.payment.IncreaseCoinRequest;
import vn.edu.fpt.horo.dto.request.payment.CreateWithDrawRequest;
import vn.edu.fpt.horo.dto.response.payment.TransactionResponse;

@RequestMapping("${app.application-context}/public/api/v1/payment")
public interface PaymentController {
    @PostMapping("/{account-id}/coin/increase")
    ResponseEntity<GeneralResponse<Object>> increaseCoin(@PathVariable("account-id") String accountId, @RequestBody IncreaseCoinRequest request);

    @PostMapping("/{account-id}/withdraw")
    ResponseEntity<GeneralResponse<CreateWithDrawResponse>> requestWithDraw(@PathVariable("account-id") String accountId, @RequestBody CreateWithDrawRequest request);

    @GetMapping
    ResponseEntity<GeneralResponse<Page<TransactionResponse>>> lstTrans(@RequestParam("transType") Long transType,
                                                                        @ParameterObject Pageable pageable);

}
