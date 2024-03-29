package vn.edu.fpt.fb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.fb.entity.Branch;
import vn.edu.fpt.fb.entity.FileInfo;

/**
 * @author namlh4
 */
@Repository
public interface BranchRepo extends JpaRepository<Branch, String> {
}
