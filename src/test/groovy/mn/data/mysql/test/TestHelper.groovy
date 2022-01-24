package mn.data.mysql.test

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import mn.data.mysql.domain.Author
import mn.data.mysql.domain.Book
import mn.data.mysql.dtos.AuthorDto
import mn.data.mysql.dtos.BookDto
import mn.data.mysql.enums.Category

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat;
import java.time.Instant;

import java.sql.Timestamp
import java.time.Instant

/**
 * Creates plain objects without database generated fields
 */
class TestHelper {
    Timestamp pubDate = new Timestamp(System.currentTimeMillis());
    String sDate1="1998-12-31";
//    Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
    java.sql.Date date1=(java.sql.Date) new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
    static Author createAuthor(String name = "Anonymous", Integer birthYear = 1982) {
        return new Author(name: name, birthYear: birthYear)
    }

    static AuthorDto createAuthorDto(String name = "Vibhu", Integer birthYear = 1982) {
        return new AuthorDto(name: name, birthYear: birthYear)
    }
    
    static Book createBook(String title = "no title", Author author = createAuthor(), pubDate, String isbn= "no isbn",Category category, Float sellPrice= 200.0 ) {
        return new Book(title: title, pubDate: pubDate, author: author, isbn: isbn, category: Category.THRILLER, sellPrice: sellPrice)
    }

    static BookDto createBookDto(String title = "no title", String author = "no name", date1, String isbn= "isbn",Category category, Float sellPrice= 200.0 ) {
        return new BookDto(title: title, pubDate: date1, author: author, isbn: isbn, category: Category.THRILLER , sellPrice: sellPrice)
    }

    static <O> HttpResponse<O> requestWithoutException(HttpClient client, HttpRequest req, Argument<O> returnedType) {
        HttpResponse<O> response

        try {
            response = client.toBlocking().exchange(req, returnedType)
        } catch (HttpClientResponseException e) {
            response = e.response
        }

        return response
    }
}
