package vn.edu.fpt.horo.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.controller.ServicePackageController;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.common.PageableResponse;
import vn.edu.fpt.horo.dto.request.service_package.CreateServicePackageRequest;
import vn.edu.fpt.horo.dto.request.service_package.UpdateServicePackageRequest;
import vn.edu.fpt.horo.dto.response.service_package.CreateServicePackageResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageDetailResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageResponse;
import vn.edu.fpt.horo.factory.ResponseFactory;
import vn.edu.fpt.horo.service.PackageService;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RestController
@RequiredArgsConstructor
@Slf4j
public class ServicePackageControllerImpl implements ServicePackageController {
    private final PackageService packageService;
    private final ResponseFactory responseFactory;

    @Override
    public ResponseEntity<GeneralResponse<CreateServicePackageResponse>> createPackageService(CreateServicePackageRequest request) {
        return responseFactory.response(packageService.createPackageService(request));
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> deleteServicePackage(String servicePackageId) {
        packageService.deleteServicePackage(servicePackageId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }

    @Override
    public ResponseEntity<GeneralResponse<Object>> updateServicePackage(String servicePackageId, UpdateServicePackageRequest request) {
        packageService.updateServicePackage(request, servicePackageId);
        return responseFactory.response(ResponseStatusEnum.SUCCESS);
    }



}
