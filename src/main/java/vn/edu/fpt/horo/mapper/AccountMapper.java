package vn.edu.fpt.horo.mapper;

import org.mapstruct.Mapper;
import vn.edu.fpt.horo.dto.admin.AllUserInfoResponse;
import vn.edu.fpt.horo.dto.admin.UserInfoDetailsResponse;
import vn.edu.fpt.horo.dto.response.account.AccountResponse;
import vn.edu.fpt.horo.dto.response.account.InformationAdvisorFollowing;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorByTopicResponse;
import vn.edu.fpt.horo.entity.Account;
import vn.edu.fpt.horo.service.FileService;

import java.util.List;


/**
 * vn.edu.fpt.accounts.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Mapper(componentModel = "spring", uses = {FileService.class})
public interface AccountMapper {

    AccountResponse mapGetAccountDetailResponse(Account account);

    InformationAdvisorFollowing mapGetAccountFollowing(Account account);

    AllUserInfoResponse mapGetUserInfo(Account account);

    UserInfoDetailsResponse mapGetUserInfoDetails(Account account);
}
