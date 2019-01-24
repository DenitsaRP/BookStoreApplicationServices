package com.scalefocus.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.bookstore.entities.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

}
