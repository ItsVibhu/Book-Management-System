package mn.data.mysql.dao;

import javax.inject.Inject;
import javax.inject.Singleton;
import mn.data.mysql.domain.Author;
import mn.data.mysql.dtos.AuthorDto;
import mn.data.mysql.mappers.AuthorMapper;
import mn.data.mysql.repositories.AuthorRepository;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class AuthorDao {
    @Inject
    private AuthorRepository authorsRepository;

    @Inject
    private AuthorMapper authorMapper;

//    public AuthorDao(AuthorRepository authorsRepository,AuthorMapper authorMapper) {
//        this.authorsRepository = authorsRepository;
//        this.authorMapper = authorMapper;
//    }

    public List<AuthorDto> findAll() {
        List<AuthorDto> authorDtos = new ArrayList<>();
        authorsRepository.findAll().forEach(author -> authorDtos.add(authorMapper.toDto(author)));
        return authorDtos;
    }

    public Optional<AuthorDto> findAuthor(String authorName) {
        return authorsRepository.findByName(authorName).map(authorMapper::toDto);
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = authorsRepository.save(authorMapper.toEntity(authorDto));
        return authorMapper.toDto(author);
    }
}
