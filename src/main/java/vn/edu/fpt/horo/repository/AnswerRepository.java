package vn.edu.fpt.horo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.horo.entity.Answer;
import vn.edu.fpt.horo.entity.Poster;

/**
 * vn.edu.fpt.horo.repository
 *
 * @author : Portgas.D.Ace
 * @created : 09/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
}
