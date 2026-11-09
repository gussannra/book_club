package se.yrgo.book_club.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.yrgo.book_club.domain.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    private List<Book> books = List.of(
            new Book("The Lord of the Rings", "J. R. R. Tolkien", "Fantasy"),
            new Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", "Fantasy"),
            new Book("Clean Code", "Robert C. Martin", "Non-fiction"),
            new Book("Clean Architecture", "Robert C. Martin", "Non-fiction"),
            new Book("Murder of the Orient Express", "Agatha Christie", "Mystery"),
            new Book("And Then There Were None", "Agatha Christie", "Mystery"),
            new Book("Pride and Prejudice", "Jane Austen", "Romance"),
            new Book("Jane Eyre", "Charlotte Brontë", "Romance")
    );

    // Show greeting and date&time
    @GetMapping("/home")
    public String greetingMessage(Model model) {
        model.addAttribute("greeting", "Welcome to the Book Club!");
        Date dateTime = new Date();
        model.addAttribute("dateTime", dateTime);

        return "home";
    }

    // Show at least four books (title, author, genre)
    @GetMapping("/books")
    public String showAllBooks(Model model) {
        model.addAttribute("books", books);
        return "booklist";
    }

    @GetMapping("/genre")
    public String showGenre(@RequestParam(required = false) String type, Model model) {

        List<Book> tempList = new ArrayList<>(books);

        if (type != null) {
            tempList = tempList.stream().filter(book -> book.getGenre().equalsIgnoreCase(type)).toList();
        }

        // if no genre type matches any book, show error message
        if (tempList.isEmpty()) {
            model.addAttribute("error", "No books found");
        }

        model.addAttribute("books", tempList);
        model.addAttribute("type", type);

        return "genre";
    }

}
