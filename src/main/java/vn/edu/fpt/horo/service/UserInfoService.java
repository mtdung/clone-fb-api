package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.dto.cache.UserInfo;

/**
 * vn.edu.fpt.accounts.service
 **/

public interface UserInfoService {

    UserInfo getUserInfo(String accountId);

    void addAvatarToUserInfo(String accountId, String avatarURL);

}
