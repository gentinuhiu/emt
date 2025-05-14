package emt.lab.model.domain;

import emt.lab.model.enumeration.CATEGORY;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private CATEGORY category;
    @ManyToOne
    private Author author;
    public Book(){}

    public Book(String title, CATEGORY category, Author author) {
        this.title = title;
        this.category = category;
        this.author = author;
    }

//    public int getAvailableCopies(){
//        return (int) copies.stream().filter(c -> !c.isRented()).count();
//    }
//    public Copy getNotRented(){
//        return copies.stream().filter(c -> !c.isRented()).findFirst().get();
//    }

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
}
