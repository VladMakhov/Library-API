package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
