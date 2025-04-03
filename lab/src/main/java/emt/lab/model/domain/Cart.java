package emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Book> review;
    @OneToMany
    private List<Copy> rented;

    public Cart() {
        this.review = new ArrayList<>();
        this.rented = new ArrayList<>();
    }

    public Cart(Long id, List<Book> review, List<Copy> rented) {
        this.id = id;
        this.review = review;
        this.rented = rented;
    }

    public Cart(List<Book> review, List<Copy> rented) {
        this.review = review;
        this.rented = rented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Book> getReview() {
        return review;
    }

    public void setReview(List<Book> review) {
        this.review = review;
    }

    public List<Copy> getRented() {
        return rented;
    }

    public void setRented(List<Copy> rented) {
        this.rented = rented;
    }
}
