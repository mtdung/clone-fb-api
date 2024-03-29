package vn.edu.fpt.fb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.fb.entity.Profile;

/**
 * @author namlh4
 */
@Repository
public interface ProfileRepo extends JpaRepository<Profile, String> {
    Profile findProfileByUserId(String userId);
}
