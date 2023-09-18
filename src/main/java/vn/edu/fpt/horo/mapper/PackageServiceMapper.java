package vn.edu.fpt.horo.mapper;

/**
 * vn.edu.fpt.accounts.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

import org.mapstruct.Mapper;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorByTopicResponse;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageResponse;
import vn.edu.fpt.horo.entity._PackageService;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackageServiceMapper {
    GetServicePackageResponse mapServicePackageResponse(_PackageService packageService);

}
