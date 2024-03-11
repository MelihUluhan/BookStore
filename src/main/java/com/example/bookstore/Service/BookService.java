package com.example.bookstore.Service;

import com.example.bookstore.Model.Author;
import com.example.bookstore.Model.Book;
import com.example.bookstore.Model.Category;
import com.example.bookstore.Repository.AuthorRepository;
import com.example.bookstore.Repository.BookRepository;
import com.example.bookstore.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        Optional<Author> optionalAuthor = authorRepository.findById(book.getAuthor().getId());
        Optional<Category> optionalCategory = categoryRepository.findById(book.getCategory().getId());

        if (optionalAuthor.isPresent() && optionalCategory.isPresent()) {
            Author author = optionalAuthor.get();
            Category category = optionalCategory.get();

            Book newBook = new Book();
            newBook.setTitle(book.getTitle());
            newBook.setAuthor(author);
            newBook.setCategory(category);
            newBook.setPageCount(book.getPageCount());
            newBook.setPrice(book.getPrice());
            newBook.setStock(book.getStock());

            return bookRepository.save(newBook);
        }
        else {
            throw new IllegalArgumentException("The book could not be created. Invalid author or category ID.");
        }
    }



    public Book updateBook(Long id, Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        }
        return null;
    }
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Book> searchBooks(String category, String author, String title) {
        if (category != null) {
            return bookRepository.findByCategoryName(category);
        } else if (author != null) {
            return bookRepository.findByAuthorFirstNameOrAuthorLastName(author, author);
        } else if (title != null) {
            return bookRepository.findByTitle(title);
        } else {
            return bookRepository.findAll();
        }
    }
    public List<Book> getBooksSortedByPrice(boolean ascending) {
        if (ascending) {
            return bookRepository.findAllByOrderByPriceAsc();
        } else {
            return bookRepository.findAllByOrderByPriceDesc();
        }
    }
    public List<Book> findByCategory_Id(Long categoryID) {
        return bookRepository.findByCategory_Id(categoryID);
    }

    public List<Book> findByAuthor_Id(Long authorID) {
        return bookRepository.findByAuthor_Id(authorID);
    }
    public List<Book> getBooksOrderByStock() {
        return bookRepository.findByOrderByStockAsc();
    }


}

