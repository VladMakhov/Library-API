package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
