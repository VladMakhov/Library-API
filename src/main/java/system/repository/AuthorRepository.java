package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import system.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(nativeQuery = true, value = """
            select b.book_name, aut.name, aut.last_name
                from authors as aut
                    join books as b
                        on b.author_id = aut.id;""")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    List<Object> findBookWithAuthor();

}
