package vn.edu.fpt.horo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.Policy;

import java.util.Optional;

/**
 * vn.edu.fpt.horo.repository
 *
 * @author : Portgas.D.Ace
 * @created : 02/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Repository
public interface PolicyRepository extends JpaRepository<Policy, String> {
    Optional<Policy> findByPolicyName(String policyName);
}
