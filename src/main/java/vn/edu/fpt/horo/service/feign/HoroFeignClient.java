package vn.edu.fpt.horo.service.feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.edu.fpt.horo.config.feign.AuthenticationFeignClientConfig;
import vn.edu.fpt.horo.config.feign.BaseConfig;

@FeignClient(name = "horo-feign-client", url = "${feign.horo.url}", configuration = BaseConfig.class)
public interface HoroFeignClient {

    @PostMapping(produces = MediaType.APPLICATION_XHTML_XML_VALUE,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<String> getHoro(@RequestBody GetHoroRequest request);
}
