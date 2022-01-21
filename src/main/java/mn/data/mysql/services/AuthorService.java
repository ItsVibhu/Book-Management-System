package mn.data.mysql.services;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import mn.data.mysql.dao.AuthorDao;
import mn.data.mysql.dtos.AuthorDto;

@Singleton
public class AuthorService {

    @Inject
    private AuthorDao authorDao;

//    public AuthorService(AuthorDao authorDao) {
//        this.authorDao = authorDao;
//    }

    public List<AuthorDto> findAll() {
        return authorDao.findAll();
    }

    public Optional<AuthorDto> findAuthor(String authorName) {
        return authorDao.findAuthor(authorName);
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        return authorDao.createAuthor(authorDto);
    }
}
