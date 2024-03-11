package com.example.bookstore.Repository;

import com.example.bookstore.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByCategory_Id(Long categoryID);
    List<Book> findByAuthor_Id(Long authorID);
    List<Book> findByCategoryName(String category);
    List<Book> findByAuthorFirstNameOrAuthorLastName(String authorFirstName, String authorLastName);
    List<Book> findByTitle(String title);
    List<Book> findAllByOrderByPriceAsc();
    List<Book> findAllByOrderByPriceDesc();
    List<Book> findByOrderByStockAsc();

}
