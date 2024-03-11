package vn.edu.fpt.fb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.fb.entity.SysUser;

/**
 * @author namlh4
 */
@Repository
public interface SysUserRepo extends JpaRepository<SysUser, String> {
}
