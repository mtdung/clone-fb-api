package vn.edu.fpt.fb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.fb.entity.FileInfo;
import vn.edu.fpt.fb.entity.SysRole;

import java.util.List;

/**
 * @author namlh4
 */
@Repository
public interface FileInfoRepo extends JpaRepository<FileInfo, String> {
}
