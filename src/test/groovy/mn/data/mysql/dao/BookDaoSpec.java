package mn.data.mysql.dao;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import mn.data.mysql.domain.Author;
import mn.data.mysql.domain.Book;
import mn.data.mysql.enums.Category;
import mn.data.mysql.repositories.BookRepository;
import mn.data.mysql.test.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class BookDaoSpec {
    @Inject
    @Client("/authors")
    HttpClient client;

    @Inject
    BookDao bookDao;

    @Inject
    BookRepository bookRepository;

    @MockBean(BookRepository.class)
    public BookRepository getBookRepository(){
        return mock(BookRepository.class);
    }

    Timestamp pubDate = new Timestamp(System.currentTimeMillis());

    @Test
    public void getAllBooksTest(){
        Author author = TestHelper.createAuthor();
        Book book1= TestHelper.createBook("Om Shanti",author, pubDate,"isbn",Category.THRILLER, 200.0F);
        Book book2= TestHelper.createBook("Shanti",author, pubDate,"isbn",Category.THRILLER, 300.0F);
        Iterable<Book> books= Arrays.asList(book1,book2);
        when(bookRepository.findAll()).thenReturn(books);
        Iterable<Book> bookDtoList= bookRepository.findAll();
        Assertions.assertEquals(books,bookDtoList);
    }

    @Test
    public void findByTitleTest(){
        Optional<Book> bookDto= Optional.ofNullable(TestHelper.createBook("Om Shanti",TestHelper.createAuthor(), pubDate, "isbn", Category.THRILLER, 200.0F));
        when(bookRepository.findByTitle("Om Shanti")).thenReturn(bookDto);
        Optional<Book> bookDto1= bookRepository.findByTitle("Om Shanti");
        Assertions.assertEquals(bookDto,bookDto1);
    }

    @Test
    public void findByIsbnTest(){
        Optional<Book> bookDto= Optional.ofNullable(TestHelper.createBook("Om Shanti", TestHelper.createAuthor(), pubDate, "isbn", Category.THRILLER, 200.0F));
        when(bookRepository.findByIsbn("isbn")).thenReturn(bookDto);
        Optional<Book> bookDto1= bookRepository.findByIsbn("isbn");
        Assertions.assertEquals(bookDto,bookDto1);
    }

    @Test
    public void findByCategoryTest(){
        Book book1= TestHelper.createBook("Om Shanti",TestHelper.createAuthor(), pubDate,"isbn",Category.THRILLER, 200.0F);
        Book book2= TestHelper.createBook("Shanti",TestHelper.createAuthor(), pubDate,"isbn",Category.THRILLER, 300.0F);
        List<Book> books= Arrays.asList(book1,book2);
        when(bookRepository.findByCategory(Category.THRILLER)).thenReturn(books);
        List<Book> bookDto1= bookRepository.findByCategory(Category.THRILLER);
        Assertions.assertEquals(books,bookDto1);
        Assertions.assertEquals(2,bookDto1.size());
    }

    @Test
    public void findByPubDateTest(){
        Author author = TestHelper.createAuthor("Vibhu");
        Book book1= TestHelper.createBook("Om Shanti",author, pubDate,"isbn",Category.THRILLER, 200.0F);
        Book book2= TestHelper.createBook("Shanti",author, pubDate,"isbn",Category.THRILLER, 230.0F);
        List<Book> books= Arrays.asList(book1,book2);
        when(bookRepository.findByPubDate(pubDate)).thenReturn(books);
        List<Book> bookDto1= bookRepository.findByPubDate(pubDate);
        Assertions.assertEquals(books,bookDto1);
    }

    @Test
    public void findBySellPriceTest(){
        Book book1= TestHelper.createBook("Om Shanti",TestHelper.createAuthor(), pubDate,"isbn",Category.THRILLER, 200.0F);
        Book book2= TestHelper.createBook("Shanti",TestHelper.createAuthor(), pubDate,"isbn",Category.THRILLER, 230.0F);
        List<Book> books= Arrays.asList(book1,book2);
        when(bookRepository.findBySellPriceGreaterThanAndSellPriceLessThanEquals(200.0F,300.0F)).thenReturn(books);
        List<Book> bookDto1= bookRepository.findBySellPriceGreaterThanAndSellPriceLessThanEquals(200.0F,300.0F);
        Assertions.assertEquals(books,bookDto1);
        Assertions.assertEquals(2,bookDto1.size());
    }

    @Test
    public void findAllByAuthorNameTest(){
        Author author = TestHelper.createAuthor("Vibhu");
        Book book1= TestHelper.createBook("Om Shanti",author, pubDate,"isbn",Category.THRILLER, 200.0F);
        Book book2= TestHelper.createBook("Shanti",author, pubDate,"isbn",Category.THRILLER, 230.0F);
        List<Book> books= Arrays.asList(book1,book2);
        when(bookRepository.findAllByAuthor(author)).thenReturn(books);
        List<Book> bookDto1= bookRepository.findAllByAuthor(author);
        Assertions.assertEquals(books,bookDto1);
        Assertions.assertEquals(2,bookDto1.size());
    }


}
