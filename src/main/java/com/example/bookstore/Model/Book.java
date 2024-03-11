package com.example.bookstore.Model;

import javax.persistence.*;
@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookID")
    private Long id;

    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "AuthorID")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "PageCount")
    private int pageCount;

    @Column(name = "Price")
    private double price;

    @Column(name = "Stock")
    private int stock;


// Getters and setters

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getStock() {return stock; }

    public void setStock(int stock) {this.stock = stock; }
}

