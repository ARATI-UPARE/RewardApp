import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
    List<RewardPoints> findByUserId(Long userId);
}