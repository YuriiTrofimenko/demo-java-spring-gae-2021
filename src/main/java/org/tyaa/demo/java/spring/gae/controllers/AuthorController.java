package org.tyaa.demo.java.spring.gae.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tyaa.demo.java.spring.gae.models.Author;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAll () {
        List<Author> authors = new ArrayList<>();
        authors.add(Author.builder().name("Bill Gates").startedAt(new Date()).build());
        authors.add(Author.builder().name("Noname X").startedAt(new Date()).build());
        return authors;
    }
}
