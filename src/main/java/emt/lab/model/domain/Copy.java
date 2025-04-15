package emt.lab.model.domain;

import emt.lab.model.enumeration.QUALITY;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Data
@Entity
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;
    private QUALITY quality;
    private boolean rented;

    public Copy(){}

    public Copy(Book book, QUALITY quality, boolean rented) {
        this.book = book;
        this.quality = quality;
        this.rented = rented;
    }
    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public QUALITY getQuality() {
        return quality;
    }

    public boolean isRented() {
        return rented;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuality(QUALITY quality) {
        this.quality = quality;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
