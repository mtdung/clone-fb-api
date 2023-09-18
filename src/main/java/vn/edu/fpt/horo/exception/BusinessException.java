package vn.edu.fpt.horo.exception;

import lombok.Getter;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;

/**
 * vn.edu.fpt.accounts.exception
 **/

@Getter
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = -7568326660433031236L;
    private final String message;
    private final ResponseStatusEnum status;

    public BusinessException(String message){
        this.status = ResponseStatusEnum.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

    public BusinessException(ResponseStatusEnum status, String message){
        this.status = status;
        this.message = message;
    }
}
