package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.common.factory.GeneralResponse;
import vn.edu.fpt.fb.common.factory.ResponseFactory;
import vn.edu.fpt.fb.controller.inter.AuthenticationController;
import vn.edu.fpt.fb.controller.inter.RedisController;
import vn.edu.fpt.fb.dto.request.LoginRequest;
import vn.edu.fpt.fb.dto.request.RedisRequest;
import vn.edu.fpt.fb.dto.response.LoginResponse;
import vn.edu.fpt.fb.service.RedisService;
import vn.edu.fpt.fb.service.inter.AuthenticationService;

@RestController
@RequiredArgsConstructor
public class RedisControllerImpl implements RedisController {

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    RedisService redisTemplate;
    @Override
    public ResponseEntity<GeneralResponse<String>> manager(RedisRequest request) {
        String response = "";
        switch (request.getAction()) {
            case "GET":
                if(redisTemplate.hasKey(request.getKey())){
                    response = redisTemplate.get(request.getKey());
                }else {
                    response = "NO VALUE";
                }
                break;
            case "PUT":
                try {
                    redisTemplate.set(request.getKey(), request.getValue());
                    response = "SUCCESS";
                }catch (Exception e){
                    response = e.getMessage();
                }
                break;
            case "DELETE":
                try {
                    redisTemplate.delete(request.getKey());
                    response = "SUCCESS";
                }catch (Exception e){
                    response = e.getMessage();
                }
                break;
            case "GETEX":
                try {
                    response = String.valueOf(redisTemplate.getEx(request.getKey()));
                }catch (Exception e){
                    response = e.getMessage();
                }
                break;
            case "SETEX":
                try {
                    response = String.valueOf(redisTemplate.setEx(request.getKey(), 5));
                }catch (Exception e){
                    response = e.getMessage();
                }
                break;
        }
        return responseFactory.response(response);
    }
}
