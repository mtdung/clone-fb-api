package vn.edu.fpt.horo.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.AppConstant;
import vn.edu.fpt.horo.entity.DisplayMessage;

import java.util.Objects;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.repository
 **/

@Service
@RequiredArgsConstructor
public class DisplayMessageRepositoryImpl implements DisplayMessageRepository{

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public Optional<DisplayMessage> findByCodeAndLanguage(String code, String language) {
        if (Objects.isNull(language)){
//            language = AppConstant.DEFAULT_LANGUAGE;
        }

//        String displayMessageStr = redisTemplate.opsForValue().get(String.format("%s:%s", code, language));
//        try {
//            return Optional.of(objectMapper.convertValue(displayMessageStr, DisplayMessage.class));
//        }catch (Exception ex) {
            return Optional.empty();
//        }
    }
}
