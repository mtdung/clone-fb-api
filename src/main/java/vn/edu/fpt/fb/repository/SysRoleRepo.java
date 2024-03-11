package vn.edu.fpt.fb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.fb.entity.SysRole;
import vn.edu.fpt.fb.entity.SysUser;

import java.util.List;

/**
 * @author namlh4
 */
@Repository
public interface SysRoleRepo extends JpaRepository<SysRole, String> {
    @Query(value = "SELECT * FROM SYS_ROLE sr JOIN USER_ROLE ur ON sr.ID = ur.ROLE_ID WHERE ur.USER_ID = :userId", nativeQuery = true)
    List<SysRole> getAllRoleByUserId(@Param("userId") String userId);
}
