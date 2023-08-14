package system.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.models.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
