package mn.data.mysql.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import mn.data.mysql.domain.Book;
import mn.data.mysql.dtos.UpdateBookRequest;
import mn.data.mysql.enums.Category;
import mn.data.mysql.repositories.BookRepository;
import reactor.core.publisher.Mono;

import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpResponse;

import mn.data.mysql.dtos.BookDto;
import mn.data.mysql.services.AuthorService;
import mn.data.mysql.services.BookService;

@Controller("/books")
@OpenAPIDefinition(
        info = @Info(
                title = "Book Management APIs",
                version = "1.0",
                description = "Book Management APIs",
                contact = @Contact( name = "Book Management System", email = "vaibhav.devidas@flipkart.com")
        ),
        tags = @Tag(name = "book-apis", description = "Book Management APIs")
)
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookRepository repository;


    public BookController(BookService bookService, AuthorService authorService, BookRepository repository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.repository = repository;
    }

    @Get("/all")
    public Mono<List<BookDto>> getAllBooks() {
        return Mono.just(bookService.findAll());
    }

    @Get
    public Mono<HttpResponse<List<BookDto>>> getBooksByAuthor(@QueryValue String author) {
//        return Mono.just(
//                authorService.findAuthor(author).map(authorDto ->
//                            HttpResponse.ok(bookService.findAllByAuthorName(authorDto.getName()))
//                        )
//                        .orElse(HttpResponse.notFound())
//        );
        return Mono.just(HttpResponse.ok(bookService.findAllByAuthorName(author)));
    }
    @Get("/title")
    public Mono<Optional<BookDto>> getBooksByTitle(@QueryValue String title) {
        return Mono.just(bookService.findByTitle(title));
    }

    @Get("/isbn")
    public Mono<Optional<BookDto>> getBooksByIsbn(@QueryValue String isbn) {
        return Mono.just(bookService.findByIsbn(isbn));
    }

    @Get("/category")
    public Mono<List<BookDto>> getBooksByCategory(@QueryValue Category category) {
        return Mono.just(bookService.findByCategory(category));
    }

    @Get("/pubDate")
    public Mono<List<BookDto>> getBooksByPubDate(@QueryValue Integer year,@QueryValue Integer month,@QueryValue Integer day) {
        return Mono.just(bookService.findByPubDate(year, month, day));
    }

    @Get("/sellingPrice")
    public Mono<List<BookDto>> getBooksBySellPrice(@QueryValue Float minPrice,@QueryValue Float maxPrice) {
        return Mono.just(bookService.findBySellPrice(minPrice, maxPrice));
    }

    @Post
    public Mono<HttpResponse> postBook(@Body @Valid BookDto bookDto) {
        Date parsedDate= Date.valueOf(bookDto.getPubDate().toString());
        bookDto.setPubDate(parsedDate);
        if (authorService.findAuthor(bookDto.getAuthor()).isEmpty() ||
            bookService.findByTitle(bookDto.getTitle()).isPresent()) {
            return Mono.just(HttpResponse.badRequest());
        }
        return Mono.just(
                bookService.create(bookDto)
                    .map(book -> HttpResponse.created(book))
                    .orElseGet(HttpResponse::badRequest)
        );
    }

    @Delete("/delete/{title:}")//Todo delete by name
    public Book delete(@PathVariable String title){
        Optional<Book> byTitle = repository.findByTitle(title);
        if (!byTitle.isPresent())
            throw new IllegalArgumentException("Does not exist: " + title);
        repository.delete(byTitle.get());
        return byTitle.get();
    }

    @Put("/update")
    public HttpResponse<Book> updateWithBody(@Body UpdateBookRequest updateBookRequest){ //Todo Put
        Book book=bookService.updateBook(updateBookRequest);
        return HttpResponse.ok(book);
    }

}
