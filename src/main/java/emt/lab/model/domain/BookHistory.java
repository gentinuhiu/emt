package emt.lab.model.domain;

import emt.lab.model.enumeration.CATEGORY;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Data
@Entity
public class BookHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private CATEGORY category;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Author author;
    @ManyToOne
    private Book book;
    private LocalDateTime stamp;

    public BookHistory() {
    }
    public BookHistory(String title, CATEGORY category, Author author, Book book, LocalDateTime stamp) {
        this.title = title;
        this.category = category;
        this.author = author;
        this.book = book;
        this.stamp = stamp;
    }

    public LocalDateTime getStamp() {
        return stamp;
    }

    public void setStamp(LocalDateTime stamp) {
        this.stamp = stamp;
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

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
