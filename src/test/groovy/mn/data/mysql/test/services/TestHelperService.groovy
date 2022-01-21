package mn.data.mysql.test.services

import javax.inject.Inject
import javax.inject.Singleton
import mn.data.mysql.domain.Author
import mn.data.mysql.domain.Book
import mn.data.mysql.test.TestHelper

import mn.data.mysql.repositories.AuthorRepository
import mn.data.mysql.repositories.BookRepository

import java.time.Instant

@Singleton
class TestHelperService {

    @Inject AuthorRepository authorRepository
    @Inject BookRepository bookRepository

    Author createAuthor(String name = "no name") {
        Author author = TestHelper.createAuthor(name)
        authorRepository.save(author)
        return author
    }

    Set<Book> createBooks(List<String> titles, Author author,Instant pubDate,String isbn,String category,Float sellPrice ) {
        Set<Book> books = new LinkedHashSet(titles.collect { String title -> createBook(title, author, pubDate, isbn, category, sellPrice) } )
        authorRepository.update(author)
        return books
    }

    Book createBook( String title, Author author, Instant pubDate, String isbn, String category, Float sellPrice) {
        Book book = TestHelper.createBook(title, author, pubDate, isbn, category, sellPrice)
        bookRepository.save(book)
        return book
    }
    
    void cleanDB() {
        authorRepository.deleteAll()
        bookRepository.deleteAll()
    }
}
