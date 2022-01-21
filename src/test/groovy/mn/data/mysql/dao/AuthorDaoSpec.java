package mn.data.mysql.dao;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import mn.data.mysql.domain.Author;
import mn.data.mysql.dtos.AuthorDto;
import mn.data.mysql.repositories.AuthorRepository;
import mn.data.mysql.test.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@MicronautTest
public class AuthorDaoSpec {
    @Inject
    @Client("/authors")
    HttpClient client;

    @Inject
    AuthorDao authorDao;

    @Inject
    AuthorRepository authorsRepository;

    @MockBean(AuthorRepository.class)
    public AuthorRepository getAuthorsRepository(){
        return mock(AuthorRepository.class);
    }

    @Test
    public void getAllAuthorsTest(){
        Author author1= new Author("Vibhu",1998);
        Author author2= new Author("Anonymous",1991);
        Iterable<Author> authors= Arrays.asList(author1,author2);
        when(authorsRepository.findAll()).thenReturn(authors);
        Iterable<Author> authorDtoList= authorsRepository.findAll();
        Assertions.assertEquals(authors,authorDtoList);
    }

    @Test
    public void findAuthorTest(){
        Optional<Author> author= Optional.ofNullable(TestHelper.createAuthor("Vibhu", 1998));
        when(authorsRepository.findByName("Vibhu")).thenReturn(author);
        Optional<Author> authorDto1= authorsRepository.findByName("Vibhu");
        Assertions.assertEquals(author,authorDto1);
    }

}
