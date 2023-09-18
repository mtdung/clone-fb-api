package vn.edu.fpt.horo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.dto.cache.UserInfo;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.service.UserInfoService;

/**
 * vn.edu.fpt.accounts.service.impl
 **/

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public UserInfo getUserInfo(String accountId) {
        try {
            String userInfoStr = redisTemplate.opsForValue().get(String.format("userinfo:%s", accountId));
            return objectMapper.readValue(userInfoStr, UserInfo.class);
        }catch (Exception ex){
            log.error("Can't get userinfo in redis: {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public void addAvatarToUserInfo(String accountId, String avatarURL) {
        try {
            String userInfoStr = redisTemplate.opsForValue().get(String.format("userinfo:%s", accountId));
            UserInfo userInfo = objectMapper.readValue(userInfoStr, UserInfo.class);
            userInfo.setAvatar(avatarURL);
            pushUserInfoToRedis(accountId, userInfo);
        }catch (Exception ex){
            throw new BusinessException("Can't avatar to userinfo: "+ ex.getMessage());
        }
    }
    private void pushUserInfoToRedis(String accountId, UserInfo userInfo){
        try {
            redisTemplate.opsForValue().set(String.format("userinfo:%s", accountId),objectMapper.writeValueAsString(userInfo));
        }catch (Exception ex){
            throw new BusinessException("Can't push userinfo to redis: "+ ex.getMessage());
        }
    }

}
