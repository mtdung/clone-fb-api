package vn.edu.fpt.horo.repository;

import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.DisplayMessage;

import java.util.Optional;

/**
 * vn.edu.fpt.accounts.repository
 **/

@Repository
public interface DisplayMessageRepository {
    Optional<DisplayMessage> findByCodeAndLanguage(String code, String language);

}

