package mn.data.mysql.services;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import mn.data.mysql.dao.AuthorDao;
import mn.data.mysql.dtos.AuthorDto;
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
public class AuthorServiceSpec {
    @Inject
    @Client("/authors")
    HttpClient client;

    @Inject
    AuthorService authorService;

    @Inject
    AuthorDao authorDao;

    private Class<mn.data.mysql.dtos.AuthorDto> AuthorDto;

    @MockBean(AuthorDao.class)
    public AuthorDao getAuthorDao(){
        return mock(AuthorDao.class);
    }

    @Test
    public void getAllAuthorsTest(){
        AuthorDto author1= new AuthorDto("Vibhu",1998);
        AuthorDto author2= new AuthorDto("Anonymous",1991);
        List<AuthorDto> authors= Arrays.asList(author1,author2);
        when(authorDao.findAll()).thenReturn(authors);
        List<AuthorDto> authorDtoList= authorService.findAll();
        Assertions.assertEquals(authors,authorDtoList);
    }

    @Test
    public void findAuthorTest(){
        Optional<AuthorDto> authorDto= Optional.ofNullable(TestHelper.createAuthorDto("Vibhu", 1998));
        when(authorDao.findAuthor("Vibhu")).thenReturn(authorDto);
        Optional<AuthorDto> authorDto1= authorService.findAuthor("Vibhu");
        Assertions.assertEquals(authorDto,authorDto1);
    }

//    @Test
//    public void createAuthorTest(){
//        AuthorDto authorDto = TestHelper.createAuthorDto();
//
//        String requestUri = String.valueOf(UriBuilder.of("/"));
//        HttpRequest request = HttpRequest.POST(requestUri, authorDto);
//
////        then: 'The author has been created'
////        rsp.status == HttpStatus.CREATED
////        rsp.body().name == authorDto.name
////        rsp.body().birthYear == authorDto.birthYear
//
//        when(authorDao.createAuthor(authorDto)).thenReturn(authorDto);
//        HttpResponse<AuthorDto> rsp = TestHelper.requestWithoutException(client, request, Argument.of(AuthorDto));
//        Assertions.assertEquals(authorDto,rsp);
//    }
}
