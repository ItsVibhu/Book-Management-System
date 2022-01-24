package mn.data.mysql.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import mn.data.mysql.enums.Category;

import java.sql.Date;

@Introspected
public class BookDto {

    @NotBlank
    private String title;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private java.sql.Date pubDate;
    @NotNull
    private String author;
    @NotNull
    private String isbn;
    @NotNull
    private Category category;
    @NotNull
    private Float sellPrice;

    public BookDto() {}

    public BookDto(String title, String author, Date pubDate, String isbn, Category category, Float sellPrice) {
        this.title = title;
        this.pubDate = pubDate;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.sellPrice = sellPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }
}
