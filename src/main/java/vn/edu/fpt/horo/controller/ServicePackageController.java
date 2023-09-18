package vn.edu.fpt.horo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.horo.dto.common.GeneralResponse;
import vn.edu.fpt.horo.dto.request.service_package.CreateServicePackageRequest;
import vn.edu.fpt.horo.dto.request.service_package.UpdateServicePackageRequest;
import vn.edu.fpt.horo.dto.response.service_package.CreateServicePackageResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageDetailResponse;


@RequestMapping("${app.application-context}/public/api/v1/service-packages")
public interface ServicePackageController {

    @PostMapping
    ResponseEntity<GeneralResponse<CreateServicePackageResponse>> createPackageService(@RequestBody CreateServicePackageRequest request);

    @DeleteMapping("/{service-package-id}")
    ResponseEntity<GeneralResponse<Object>> deleteServicePackage(@PathVariable(name = "service-package-id") String servicePackageId);

    @PutMapping("/{service-package-id}")
    ResponseEntity<GeneralResponse<Object>> updateServicePackage(@PathVariable(name = "service-package-id") String servicePackageId, @RequestBody UpdateServicePackageRequest request);


}
