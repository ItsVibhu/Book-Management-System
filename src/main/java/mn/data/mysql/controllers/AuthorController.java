package mn.data.mysql.controllers;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.inject.Inject;
import lombok.AllArgsConstructor;
import mn.data.mysql.domain.Author;
import mn.data.mysql.domain.Book;
import mn.data.mysql.repositories.AuthorRepository;
import reactor.core.publisher.Mono;

import io.micronaut.http.HttpResponse;

import mn.data.mysql.dtos.AuthorDto;
import mn.data.mysql.services.AuthorService;

@Controller("/authors")
@OpenAPIDefinition(
        info = @Info(
                title = "Author Management APIs",
                version = "1.0",
                description = "Author Management APIs",
                contact = @Contact( name = "Author Management System", email = "vaibhav.devidas@flipkart.com")
        ),
        tags = @Tag(name = "author-apis", description = "Author Management APIs")
)

public class AuthorController {
    @Inject
    private  AuthorService authorService;
    @Inject
    private  AuthorRepository repository;


    @Get("/all")
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAll();
    }

    @Get
    public Mono<HttpResponse<AuthorDto>> getAuthor(@QueryValue String name) {
        return Mono.just(
                authorService.findAuthor(name).map(HttpResponse::ok)
                        .orElse(HttpResponse.notFound())
        );
    }

    @Post
    public HttpResponse<AuthorDto> createAuthor(@Body @Valid AuthorDto authorDto) {
        return authorService.findAuthor(authorDto.getName()).isPresent() ?
                        HttpResponse.badRequest()
                        :
                        HttpResponse.created(authorService.createAuthor(authorDto)
        );
    }
    @Delete("/delete/{id:2}")
    public Author delete(@PathVariable Long id){
        Optional<Author> byId = repository.findById(id);
        if (!byId.isPresent())
            throw new IllegalArgumentException("Does not exist: " + id);
        repository.delete(byId.get());
        return byId.get();
    }

    @Post("/update")
    public Author updateWithBody(@Body Author author){
        Optional<Author> byId = repository.findById(author.getId());
        Author entity = byId.get();
        entity.setName(author.getName());
        entity.setBirthYear(author.getBirthYear());
        return repository.update(entity);
    }
}
