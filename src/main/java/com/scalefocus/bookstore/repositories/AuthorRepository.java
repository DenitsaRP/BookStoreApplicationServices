package com.scalefocus.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.bookstore.entities.Authors;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Long> {

	public Authors findByName(String name);
}