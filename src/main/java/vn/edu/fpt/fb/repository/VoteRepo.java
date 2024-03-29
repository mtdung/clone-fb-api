package vn.edu.fpt.fb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.fb.entity.FileInfo;
import vn.edu.fpt.fb.entity.Vote;

/**
 * @author namlh4
 */
@Repository
public interface VoteRepo extends JpaRepository<Vote, String> {
}
