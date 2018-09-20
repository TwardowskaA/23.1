package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @GetMapping("/ksiazka/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/edytuj/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id);
        model.addAttribute("id",id);
        model.addAttribute("book", book);
        return "edycja";
    }

    @PostMapping("/edytuj")
    public String editBook(Book book) {
        bookRepository.update(book);
        return "redirect:/";
    }

    @GetMapping("/usun/{id}")
    public String removeBook(@PathVariable Long id){
        bookRepository.remove(id);
        return "redirect:/";
    }
}
