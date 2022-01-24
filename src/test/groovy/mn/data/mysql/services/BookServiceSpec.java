package mn.data.mysql.services;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import mn.data.mysql.dao.BookDao;
import mn.data.mysql.domain.Book;
import mn.data.mysql.dtos.BookDto;
import mn.data.mysql.enums.Category;
import mn.data.mysql.test.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class BookServiceSpec {
    @Inject
    @Client("/books")
    HttpClient client;

    @Inject
    BookService bookService;

    @Inject
    BookDao bookDao;

    public BookServiceSpec() throws ParseException {
    }

    @MockBean(BookDao.class)
    public BookDao getBookDao(){
        return mock(BookDao.class);
    }

    String sDate1="1998-12-31";
////    Date date1= (Date) new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
//java.util.Date utilDate = new java.util.Date();
//    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
java.util.Date newDate=  new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
//java.sql.Date date1= (java.sql.Date) new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
java.sql.Date date1= new java.sql.Date(newDate.getTime());

    Timestamp pubDate = new Timestamp(System.currentTimeMillis());

    @Test
    public void getAllBooksTest(){
        BookDto book1= TestHelper.createBookDto("Om Shanti","Vibhu", date1,"isbn",Category.THRILLER, 200.0F);
        BookDto book2= TestHelper.createBookDto("Shanti","Vibhu", date1,"isbn",Category.THRILLER, 300.0F);
        List<BookDto> books= Arrays.asList(book1,book2);
        when(bookDao.findAll()).thenReturn(books);
        List<BookDto> bookDtoList= bookService.findAll();
        Assertions.assertEquals(books,bookDtoList);
    }

    @Test
    public void findByTitleTest(){
        Optional<BookDto> bookDto= Optional.ofNullable(TestHelper.createBookDto("Om Shanti", "Vibhu", date1, "isbn", Category.THRILLER, 200.0F));
        when(bookDao.findByTitle("Om Shanti")).thenReturn(bookDto);
        Optional<BookDto> bookDto1= bookService.findByTitle("Om Shanti");
        Assertions.assertEquals(bookDto,bookDto1);
    }

    @Test
    public void findByIsbnTest(){
        Optional<BookDto> bookDto= Optional.ofNullable(TestHelper.createBookDto("Om Shanti", "Vibhu", date1 , "isbn", Category.THRILLER, 200.0F));
        when(bookDao.findByIsbn("isbn")).thenReturn(bookDto);
        Optional<BookDto> bookDto1= bookService.findByIsbn("isbn");
        Assertions.assertEquals(bookDto,bookDto1);
    }

    @Test
    public void findByCategoryTest(){
        BookDto book1= TestHelper.createBookDto("Om Shanti","Vibhu", date1,"isbn",Category.THRILLER, 200.0F);
        BookDto book2= TestHelper.createBookDto("Shanti","Vibhu", date1,"isbn",Category.THRILLER, 300.0F);
        List<BookDto> books= Arrays.asList(book1,book2);
        when(bookDao.findByCategory(Category.THRILLER)).thenReturn(books);
        List<BookDto> bookDto1= bookService.findByCategory(Category.THRILLER);
        Assertions.assertEquals(books,bookDto1);
        Assertions.assertEquals(2,bookDto1.size());
    }

    @Test
    public void findByPubDateTest(){
        BookDto book1= TestHelper.createBookDto("Om Shanti","Vibhu", date1,"isbn",Category.THRILLER, 200.0F);
        BookDto book2= TestHelper.createBookDto("Shanti","Vibhu", date1,"isbn",Category.THRILLER, 230.0F);
        List<BookDto> books= Arrays.asList(book1,book2);
        when(bookDao.findByPubDate(2018,01,01)).thenReturn(books);
        List<BookDto> bookDto1= bookService.findByPubDate(2018,01,01);
        Assertions.assertEquals(books,bookDto1);
    }

    @Test
    public void findBySellPriceTest(){
        BookDto book1= TestHelper.createBookDto("Om Shanti","Vibhu", date1,"isbn",Category.THRILLER, 200.0F);
        BookDto book2= TestHelper.createBookDto("Shanti","Vibhu", date1,"isbn",Category.THRILLER, 230.0F);
        List<BookDto> books= Arrays.asList(book1,book2);
        when(bookDao.findBySellPrice(200.0F,300.0F)).thenReturn(books);
        List<BookDto> bookDto1= bookService.findBySellPrice(200.0F,300.0F);
        Assertions.assertEquals(books,bookDto1);
        Assertions.assertEquals(2,bookDto1.size());
    }

    @Test
    public void findAllByAuthorNameTest(){
        BookDto book1= TestHelper.createBookDto("Om Shanti","Vibhu", date1,"isbn",Category.THRILLER, 200.0F);
        BookDto book2= TestHelper.createBookDto("Shanti","Vibhu", date1,"isbn",Category.THRILLER, 230.0F);
        List<BookDto> books= Arrays.asList(book1,book2);
        when(bookDao.findAllByAuthorName("Vibhu")).thenReturn(books);
        List<BookDto> bookDto1= bookService.findAllByAuthorName("Vibhu");
        Assertions.assertEquals(books,bookDto1);
        Assertions.assertEquals(2,bookDto1.size());
    }
}
