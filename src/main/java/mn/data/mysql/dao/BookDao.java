package mn.data.mysql.dao;

import javax.inject.Inject;
import javax.inject.Singleton;
import mn.data.mysql.domain.Book;
import mn.data.mysql.dtos.BookDto;
import mn.data.mysql.dtos.UpdateBookRequest;
import mn.data.mysql.enums.Category;
import mn.data.mysql.exception.BaseException;
import mn.data.mysql.mappers.BookMapper;
import mn.data.mysql.repositories.AuthorRepository;
import mn.data.mysql.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
//import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class BookDao {
    @Inject
    private BookRepository bookRepository;
    @Inject
    private BookMapper bookMapper;
    @Inject
    private AuthorRepository authorRepository;

    private final Logger logger= LoggerFactory.getLogger(BookDao.class);

//    public BookDao(BookRepository booksRepository, BookMapper bookMapper, AuthorRepository authorRepository) {
//        this.bookRepository = booksRepository;
//        this.bookMapper = bookMapper;
//        this.authorRepository = authorRepository;
//    }

    public List<BookDto> findAll() {
        List<BookDto> bookDtos = new ArrayList<>();
        bookRepository.findAll().forEach(book -> bookDtos.add(bookMapper.toDto(book)));
        return bookDtos;
    }

    public Optional<BookDto> findByTitle(String title) {
        return bookRepository.findByTitle(title).map(bookMapper::toDto);
    }

    public Optional<BookDto> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).map(bookMapper::toDto);
    }

    public List<BookDto> findByCategory(Category category) {
        List<BookDto> bookDtos = new ArrayList<>();
        bookRepository.findByCategory(category).forEach(book -> bookDtos.add(bookMapper.toDto(book)));
        return bookDtos;
    }

    public List<BookDto> findByPubDate(Integer year, Integer month, Integer day) {
        List<BookDto> bookDtos = new ArrayList<>();
        LocalDate date = LocalDate.of(year, month, day);
        Timestamp ts = Timestamp.valueOf(date.atTime(LocalTime.MIDNIGHT));
        bookRepository.findByPubDate(ts).forEach(book -> bookDtos.add(bookMapper.toDto(book)));
        return bookDtos;
    }

    public List<BookDto> findBySellPrice(Float minPrice,Float maxPrice) {
        List<BookDto> bookDtos = new ArrayList<>();
        bookRepository.findBySellPriceGreaterThanAndSellPriceLessThanEquals(minPrice, maxPrice).forEach(book -> bookDtos.add(bookMapper.toDto(book)));
        return bookDtos;
    }

    public List<BookDto> findAllByAuthorName(String authorName) {
        List<BookDto> bookDtos = new ArrayList<>();

        authorRepository.findByName(authorName).ifPresent(author ->
                bookRepository.findAllByAuthor(author).forEach(book ->
                        bookDtos.add(bookMapper.toDto(book)))
        );
        return bookDtos;
    }

    public Optional<BookDto> create(BookDto bookDto) {
        Timestamp ts=new Timestamp(bookDto.getPubDate().getTime());
        return authorRepository.findByName(bookDto.getAuthor()).map(author ->
                        bookRepository.save(new Book(bookDto.getTitle(), author,ts, bookDto.getIsbn(), bookDto.getCategory(), bookDto.getSellPrice())))
                .map(bookMapper::toDto);
    }

    private Book getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> {
            logger.error("Book Not Found! ");
            return new BaseException("Book Not Found! ");});
        return book;
    }

    public Book updateBook(UpdateBookRequest updateBookRequest) {
//               Optional<Book> byId = bookRepository.findById(updateBookRequest.getId());
        Book book = getBook(updateBookRequest.getId());
        if(updateBookRequest.getTitle()!= null)
            book.setTitle(updateBookRequest.getTitle());
        if(updateBookRequest.getPubDate()!=null)
            book.setPubDate(updateBookRequest.getPubDate());
        if(updateBookRequest.getAuthor()!=null)
            book.setAuthor(updateBookRequest.getAuthor());
        if(updateBookRequest.getIsbn()!=null)
            book.setIsbn(updateBookRequest.getIsbn());
        if(updateBookRequest.getCategory()!=null)
            book.setCategory(updateBookRequest.getCategory());
        if(updateBookRequest.getSellPrice()!=null)
            book.setSellPrice(updateBookRequest.getSellPrice());

        logger.info("Book Updated Successfully");
        return bookRepository.update(book);
    }
}
