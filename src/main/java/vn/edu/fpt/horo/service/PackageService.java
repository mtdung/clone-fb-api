package vn.edu.fpt.horo.service;

import vn.edu.fpt.horo.dto.request.service_package.CreateServicePackageRequest;
import vn.edu.fpt.horo.dto.request.service_package.UpdateServicePackageRequest;
import vn.edu.fpt.horo.dto.response.service_package.CreateServicePackageResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageDetailResponse;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
public interface PackageService {


    void updateServicePackage(UpdateServicePackageRequest request, String servicePackageId);

    void deleteServicePackage(String servicePackageId);


    CreateServicePackageResponse createPackageService(CreateServicePackageRequest request);


}
