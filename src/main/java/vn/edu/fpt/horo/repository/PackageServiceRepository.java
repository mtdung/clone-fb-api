package vn.edu.fpt.horo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.Topic;
import vn.edu.fpt.horo.entity._PackageService;
import vn.edu.fpt.horo.entity.custom.TopicNumberUse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Repository
public interface PackageServiceRepository extends JpaRepository<_PackageService, String> {
    @Query(value = "select t.topic_id as topicId, t.topic_name as topicName, count(p.package_service_id) as numberUseTopic, t.is_active as isActive from topics t  left join package_services p on p.topic = t.topic_id group by t.topic_id order by numberUseTopic desc",nativeQuery = true)
    List<TopicNumberUse> getTopicNumberUse();

    List<_PackageService> findByTopicTopicId(String topic);

    @Query(value = "select AVG(price) from package_services where advisor = :advisorId",nativeQuery = true)
    BigDecimal getAvgPriceByAdvisorId(String advisorId);

}
