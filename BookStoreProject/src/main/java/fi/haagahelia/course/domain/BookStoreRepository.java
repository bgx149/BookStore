package fi.haagahelia.course.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookStoreRepository extends CrudRepository<Book, Long> {

    List<Book> findBytitle(String title);
    
}