package mn.data.mysql.services;

import java.sql.Date;
import java.util.Optional;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.micronaut.http.annotation.QueryValue;
import mn.data.mysql.dao.BookDao;
import mn.data.mysql.domain.Book;
import mn.data.mysql.dtos.BookDto;
import mn.data.mysql.dtos.UpdateBookRequest;
import mn.data.mysql.enums.Category;

@Singleton
public class BookService {
    @Inject
    private BookDao bookDao;

//    //public BookService(BookDao bookDao) {
//        this.bookDao = bookDao;
//    }

    public List<BookDto> findAll() {
        return bookDao.findAll();
    }

    public Optional<BookDto> findByTitle(String title) {
        return bookDao.findByTitle(title);
    }

    public Optional<BookDto> findByIsbn(String isbn) {
        return bookDao.findByIsbn(isbn);
    }

    public List<BookDto> findByCategory(Category category) {
        return bookDao.findByCategory(category);
    }

    public List<BookDto> findByPubDate(Integer year, Integer month, Integer day) {
        return bookDao.findByPubDate(year,month,day);
    }

    public List<BookDto> findBySellPrice(Float minPrice,Float maxPrice) {
        return bookDao.findBySellPrice(minPrice,maxPrice);
    }

    public List<BookDto> findAllByAuthorName(String authorName) {
        return bookDao.findAllByAuthorName(authorName);
    }

    public Optional<BookDto> create(BookDto bookDto) {
        return bookDao.create(bookDto);
    }

    public Book updateBook(UpdateBookRequest updateBookRequest) {
        return bookDao.updateBook(updateBookRequest);
    }
}
