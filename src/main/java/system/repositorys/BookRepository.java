package system.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
