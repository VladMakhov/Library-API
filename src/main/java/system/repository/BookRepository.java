package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
