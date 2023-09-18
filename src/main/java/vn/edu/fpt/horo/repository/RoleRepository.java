package vn.edu.fpt.horo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity._Role;

import java.util.Optional;

/**
 * vn.edu.fpt.accounts.repository
 **/

@Repository
public interface RoleRepository extends JpaRepository<_Role, String> {

    Optional<_Role> findByRoleName(String roleName);
}
