package vn.edu.fpt.fb.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.fb.dto.common.GeneralResponse;
import vn.edu.fpt.fb.dto.request.service_package.CreateServicePackageRequest;
import vn.edu.fpt.fb.dto.request.service_package.UpdateServicePackageRequest;
import vn.edu.fpt.fb.dto.response.service_package.CreateServicePackageResponse;
import vn.edu.fpt.fb.factory.ResponseFactory;

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
