package br.com.gabriel.vo.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "author",
        "launchDate",
        "price",
        "title"
})
public class BookVO extends RepresentationModel<PersonVO> implements Serializable {

    private static final long serialVersionUID = -3102470079487792616L;

    @JsonProperty("id")
    private Long key;
    private String author;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime launchDate;
    private Double price;
    private String title;


    public void setKey(Long key) {
        this.key = key;
    }

    public Long getKey() {
        return key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookVO book = (BookVO) o;
        return Objects.equals(key, book.key) && Objects.equals(author, book.author) && Objects.equals(launchDate, book.launchDate) && Objects.equals(price, book.price) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, author, launchDate, price, title);
    }

}
