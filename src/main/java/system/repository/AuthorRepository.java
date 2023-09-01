package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
