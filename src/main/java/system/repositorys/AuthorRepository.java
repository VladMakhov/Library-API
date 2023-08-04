package system.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import system.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
