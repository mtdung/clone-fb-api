package vn.edu.fpt.horo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.Topic;

import java.util.List;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    @Query(value = "update topics set is_active = :isActive where topic_id = :topicId", nativeQuery = true)
    void updateTopicActive(Boolean isActive, String topicId);

    Topic findByTopicId(String topicId);

    List<Topic> findAllByIsActive(boolean isActive);
}
