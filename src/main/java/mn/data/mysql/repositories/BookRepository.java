package mn.data.mysql.repositories;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import mn.data.mysql.domain.Author;
import mn.data.mysql.domain.Book;
import mn.data.mysql.enums.Category;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByAuthor(Author author);
    Optional<Book> findByTitle(String title);
    //Optional<Book> findById(Integer id);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByCategory(Category category);
    //@Query(nativeQuery = true)
    List<Book> findByPubDate(Timestamp pubDate);
    List<Book> findBySellPriceGreaterThanAndSellPriceLessThanEquals(Float minPrice,Float maxPrice);
}

