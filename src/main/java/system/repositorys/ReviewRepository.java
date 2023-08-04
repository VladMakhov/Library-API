package system.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
