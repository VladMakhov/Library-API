package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system.model.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByNameAndLastName(String name, String listName);
}
