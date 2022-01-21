package mn.data.mysql.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import mn.data.mysql.enums.Category;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="TITLE", unique = true)
    @Size(max = 150)
    private String title;

    @Column(name="PUB_DATE")
    private Timestamp pubDate;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "AUTHOR")
    private Author author;

    @Column(name="ISBN")
    private String isbn;

    @Column(name="CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name="SELLING_PRICE")
    private Float sellPrice;

    public Book() {}

    public Book(String title, Author author, Timestamp pubDate, String isbn, Category category, Float sellPrice) {
        this.title = title;
        this.pubDate = pubDate;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.sellPrice = sellPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getPubDate() {
        return pubDate;
    }

    public void setPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }
}