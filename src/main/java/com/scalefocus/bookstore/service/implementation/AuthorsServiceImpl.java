package com.scalefocus.bookstore.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.enums.ErrorMessages;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.repositories.AuthorRepository;
import com.scalefocus.bookstore.service.IAuthorServices;

@Service
public class AuthorsServiceImpl implements IAuthorServices {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Authors> getAuthorsInBookstore() throws Exception {
		return authorRepository.findAll();
	}

	@Override
	public Authors getAuthorById(Long author_id) throws BookStoreServiceException {
		return authorRepository.findById(author_id)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.AUTHOR_NOT_FOUND));

	}

	@Override
	public Authors addAuthors(Authors author) {
		return authorRepository.save(author);
	}

}
