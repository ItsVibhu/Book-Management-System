package mn.data.mysql.mappers;

import javax.inject.Singleton;

import mn.data.mysql.domain.Author;
import mn.data.mysql.domain.Book;
import mn.data.mysql.dtos.AuthorDto;
import mn.data.mysql.dtos.BookDto;

import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Singleton
public class BookMapper {

    final private AuthorMapper authorMapper;

    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public BookDto toDto(Book book) {
        Date date=new Date(book.getPubDate().getTime());
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        String newDate= formatter.format(date);

        AuthorDto authorDto = authorMapper.toDto(book.getAuthor());
        return new BookDto(book.getTitle(), authorDto.getName(),date, book.getIsbn(), book.getCategory(), book.getSellPrice());
    }
}
