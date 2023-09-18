package vn.edu.fpt.horo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.AppConfig;

import java.util.Optional;

/**
 * vn.edu.fpt.accounts.repository
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Repository
public interface AppConfigRepository extends JpaRepository<AppConfig, String> {

    Optional<AppConfig> findByConfigKey(String configKey);
}
