package vn.edu.fpt.fb.common.factory;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.edu.fpt.fb.common.constant.ResponseStatusEnum;

import java.util.Objects;

/**
 * vn.edu.fpt.accounts.factory
 **/

@Component
public class ResponseFactory {


    public <T> ResponseEntity<GeneralResponse<T>> response(T data, ResponseStatusEnum status, String message){
        ResponseStatusCustom responseStatus = new ResponseStatusCustom();
        responseStatus.setStatus(status.getStatus());
        responseStatus.setCode(status.getCode());
        if(Objects.nonNull(message)){
            responseStatus.setMessage(message);
        }

        return ResponseEntity
                .status(status.getStatus())
                .body(createResponse(data, responseStatus));
    }
    public <T> ResponseEntity<GeneralResponse<T>> response(T data, ResponseStatusEnum status){
        return response(data, status, null);
    }

    public <T> ResponseEntity<GeneralResponse<T>> response(T data){
        return response(data, ResponseStatusEnum.SUCCESS, null);
    }

    public ResponseEntity<GeneralResponse<Object>> response(ResponseStatusEnum status, String message){
        return response(null, status, message);
    }

    public ResponseEntity<GeneralResponse<Object>> response(ResponseStatusEnum status){
        return response(null, status, null);
    }

    private <T> GeneralResponse<T> createResponse(T data, ResponseStatusCustom status){
        GeneralResponse<T> generalResponse = new GeneralResponse<>();
        generalResponse.setStatus(status);
        generalResponse.setData(data);
        return generalResponse;
    }

}
