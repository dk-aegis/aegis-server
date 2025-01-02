package aegis.server.domain.survey.repository;

import aegis.server.domain.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("SELECT s FROM Survey s where s.member.id =:memberId")
    Optional<Survey> findByMemberId(Long memberId);
}
