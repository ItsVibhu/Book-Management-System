package mn.data.mysql.controller;

import io.micronaut.test.annotation.MockBean;
import mn.data.mysql.controllers.AuthorController;
import mn.data.mysql.dtos.AuthorDto;
import mn.data.mysql.services.AuthorService;
import mn.data.mysql.services.AuthorServiceSpec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest
//@RunWith(MockitoJUnitRunner.class)
public class AuthorControllerSpec {

    @Inject
    AuthorController authorController;

//    @Mock
//    AuthorDao authorDao;

    @Inject
    AuthorService authorService;

    @MockBean(AuthorServiceSpec.class)
    public AuthorServiceSpec getAuthorService(){
        return mock(AuthorServiceSpec.class);
    }

//    @Inject
//    @Client("/authors")
//    HttpClient client;

//    @BeforeEach
//    void setUp() {
//        authorController = new AuthorController(authorService,authorRepository);
//    }

//    @Mock
//    AuthorRepository authorRepository;

//    @Autowired
//    private MockMvc mockMvc;

//    private Mono<List<AuthorDto>> getList1(){
//        AuthorDto author1= new AuthorDto("Vibhu",1998);
//        AuthorDto author2= new AuthorDto("Anonymous",1991);
//        return Mono.just(List.of(author1, author2));
//    }

//    @Test
//    public void getAllAuthorsTest(){
//        AuthorDto author1= new AuthorDto("Vibhu",1998);
//        AuthorDto author2= new AuthorDto("Anonymous",1991);
//        List<AuthorDto> authors= Arrays.asList(author1,author2);
////        AuthorService authorService=mock(AuthorService.class);
////        authorController = new AuthorController(authorService,authorRepository);
//        //Mono<List<AuthorDto>> authorNew= new Mono<List<AuthorDto>>("Vibhu",1998);
////        Flux.merge(getList1())
////                .flatMapIterable(Function.identity())
////                .collectList()
////        AuthorController authorController1=Mockito.mock(AuthorController.class);
//        when(authorService.findAll()).thenReturn(authors);
////        doReturn(authors).when(authorService).findAll();
//        List<AuthorDto> authorDtoList= authorController.getAllAuthors();
//        Assertions.assertEquals(authors,authorDtoList);
////        RequestBuilder request = MockMvcRequestBuilders
////                .get("/authors")
////                .accept(MediaType.APPLICATION_JSON);
//
////        MvcResult result = mockMvc.perform(request).andReturn();
////        assertEquals("index", result.getResponse().getContentAsString());
//    }

//    @Test
//    public void getAuthorByName(){
//        AuthorDto author1= new AuthorDto("Vibhu",1998);
//        Author author= new Author("Vibhu",1998);
//        //AuthorDto authorDto=AuthorDto.builder().name("Vibhu").birthYear(1998).build();
//        Mockito.when(authorController.getAuthor(anyString())).thenReturn(author);
//    }

//    @Test
//    void createAuthorTest() throws IOException {
////
////        Mockito.when(storeService.createStore(createStoreRequest)).thenReturn(storeProfile);
////        Mockito.when(storeDTOAssembler.assemble(storeProfile)).thenReturn(storeDTO);
////
////        HttpResponse<Store> response = client.exchange(HttpRequest.POST("/", createStoreRequest),
////                Store.class).blockingFirst();
////
////        Assertions.assertEquals(HttpStatus.CREATED, response.getStatus(),
////                "response status should be HttpStatus CREATED");
////        Assertions
////                .assertTrue(response.getBody(Store.class).isPresent(), "response body should be present.");
////
////        Assertions.assertEquals(storeDTO, response.getBody(Store.class).get(),
////                "The expected store object and the store object created must be equal");
////
////        Mockito.verify(storeService).createStore(createStoreRequest);
////        Mockito.verify(storeDTOAssembler).assemble(storeProfile);
//        AuthorDto authorDto=TestHelper.createAuthorDto();
//        //when(authorController.createAuthor(authorDto)).thenReturn(authorDto);
//        doReturn("Author Created").when(authorController).createAuthor(authorDto);
//        String requestUri = String.valueOf(UriBuilder.of("/"));
//        HttpRequest request = HttpRequest.POST(requestUri, authorDto);
//        HttpResponse<AuthorDto> rsp = TestHelper.requestWithoutException(client, request, Argument.of(authorDto));
//    }




}
