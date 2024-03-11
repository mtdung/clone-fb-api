package vn.edu.fpt.fb.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import vn.edu.fpt.fb.common.constant.ResponseStatusEnum;
import vn.edu.fpt.fb.common.constant.factory.GeneralResponse;
import vn.edu.fpt.fb.common.constant.factory.ResponseFactory;
import vn.edu.fpt.fb.common.constant.factory.ResponseStatusCustom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * vn.edu.fpt.accounts.exception
 **/

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ResponseFactory responseFactory;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        GeneralResponse<Object> generalResponse = new GeneralResponse<>();
        generalResponse.setStatus(new ResponseStatusCustom(ResponseStatusEnum.UNAUTHORIZED));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream responseStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(responseStream, generalResponse);
        responseStream.flush();
    }
}
