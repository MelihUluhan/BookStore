package com.example.bookstore.Controller;

import com.example.bookstore.Model.Book;
import com.example.bookstore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("/category/{categoryID}")
    public List<Book> findByCategory_Id(@PathVariable Long categoryID) {
        return bookService.findByCategory_Id(categoryID);
    }

    @GetMapping("/author/{authorID}")
    public List<Book> findByAuthor_Id(@PathVariable Long authorID) {
        return bookService.findByAuthor_Id(authorID);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String category,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) String title) {
        return bookService.searchBooks(category, author, title);
    }

    @GetMapping("/sortedByPrice")
    public List<Book> getBooksSortedByPrice(@RequestParam(required = false) boolean ascending) {
        return bookService.getBooksSortedByPrice(ascending);
    }

    @GetMapping("/sortedByStock")
    public List<Book> getBooksSortedByStock() {
        return bookService.getBooksOrderByStock();
    }

}
