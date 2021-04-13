package org.tyaa.demo.java.spring.gae.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tyaa.demo.java.spring.gae.models.Author;
import org.tyaa.demo.java.spring.gae.repositories.AuthorDataStoreDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorDataStoreDao authorDataStoreDao;
    /* @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAll () {
        List<Author> authors = new ArrayList<>();
        authors.add(Author.builder().name("Bill Gates").startedAt(new Date()).build());
        authors.add(Author.builder().name("Noname X").startedAt(new Date()).build());
        return authors;
    } */
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAll () {
        return authorDataStoreDao.read();
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@RequestBody Author author) {
        authorDataStoreDao.create(author);
    }
}
