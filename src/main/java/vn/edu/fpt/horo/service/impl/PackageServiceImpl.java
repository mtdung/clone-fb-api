package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.dto.request.service_package.CreateServicePackageRequest;
import vn.edu.fpt.horo.dto.request.service_package.UpdateServicePackageRequest;
import vn.edu.fpt.horo.dto.response.service_package.CreateServicePackageResponse;
import vn.edu.fpt.horo.dto.response.service_package.GetServicePackageDetailResponse;
import vn.edu.fpt.horo.entity.Advisor;
import vn.edu.fpt.horo.entity.Topic;
import vn.edu.fpt.horo.entity._PackageService;
import vn.edu.fpt.horo.exception.BusinessException;
import vn.edu.fpt.horo.repository.AdvisorRepository;
import vn.edu.fpt.horo.repository.PackageServiceRepository;
import vn.edu.fpt.horo.repository.TopicRepository;
import vn.edu.fpt.horo.service.PackageService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {
    private final TopicRepository topicRepository;
    private final PackageServiceRepository packageServiceRepository;

    private final AdvisorRepository advisorRepository;

    @Override
    public void updateServicePackage(UpdateServicePackageRequest request, String servicePackageId) {
        _PackageService packageService = packageServiceRepository.findById(servicePackageId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Service Package ID not exist"));
        if (Objects.nonNull(request.getPrice())) {
            packageService.setPrice(request.getPrice());
        }
        if (Objects.nonNull(request.getSlot())) {
            packageService.setSlot(request.getSlot());
        }
        if (Objects.nonNull(request.getServiceName())) {
            packageService.setServiceName(request.getServiceName());
        }
        try {
            packageServiceRepository.save(packageService);
            log.info("Update Service Package success");
        } catch (Exception ex) {
            throw new BusinessException("Can't update Service Package in database: " + ex.getMessage());
        }
    }

    @Override
    public void deleteServicePackage(String servicePackageId) {
        _PackageService packageService = packageServiceRepository.findById(servicePackageId)
                .orElseThrow(() -> new BusinessException(ResponseStatusEnum.BAD_REQUEST, "Service Package ID not exist"));
        try{
            packageServiceRepository.deleteById(servicePackageId);
            log.info("Delete Service Package success");
        }catch (Exception ex){
            throw new BusinessException("Can't delete Service Package: " + ex.getMessage());
        }
    }


    @Override
    public CreateServicePackageResponse createPackageService(CreateServicePackageRequest request) {
        Advisor advisor = advisorRepository.findById(request.getAdvisorId())
                .orElseThrow();
        Topic topic = topicRepository.findById(request.getTopic())
                .orElseThrow();
        _PackageService packageService = _PackageService.builder()
                .serviceName(request.getServiceName())
                .description(request.getDescription())
                .isActive(request.getIsActive())
                .price(request.getPrice())
                .topic(topic)
                .advisor(advisor)
                .build();
        packageService = packageServiceRepository.save(packageService);
        List<_PackageService> packageServices = advisor.getPackageServices();
        packageServices.add(packageService);
        List<Topic> topics = advisor.getTopics();
        topics.add(topic);
        advisor.setTopics(topics);
        advisor.setPackageServices(packageServices);
        advisorRepository.save(advisor);
        BigDecimal avgPrice = packageServiceRepository.getAvgPriceByAdvisorId(advisor.getAdvisorId());
        advisor.setAvgPrice(avgPrice);
        advisorRepository.save(advisor);

        return CreateServicePackageResponse.builder()
                .servicePackageId(packageService.getPackageServiceId())
                .build();
    }

}
