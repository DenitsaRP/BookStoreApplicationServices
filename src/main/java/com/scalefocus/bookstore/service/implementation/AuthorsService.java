package com.scalefocus.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.AuthorsList;
import com.scalefocus.bookstore.enums.ErrorMessages;
import com.scalefocus.bookstore.exceptions.BookStoreServiceException;
import com.scalefocus.bookstore.repositories.AuthorRepository;
import com.scalefocus.bookstore.service.IAuthorServices;

@Service
public class AuthorsService implements IAuthorServices {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public AuthorsList getAuthorsInBookstore() {
		return new AuthorsList(authorRepository.findAll());
	}

	@Override
	public Authors getAuthorById(Long authorId) throws BookStoreServiceException {
		if (authorId == null) {
			throw new BookStoreServiceException(ErrorMessages.NULL_VALUE);
		}
		return authorRepository.findById(authorId)
				.orElseThrow(() -> new BookStoreServiceException(ErrorMessages.AUTHOR_NOT_FOUND));

	}

	@Override
	public Authors addAuthors(Authors author) throws BookStoreServiceException {
		if (author == null) {
			throw new BookStoreServiceException(ErrorMessages.NULL_VALUE);
		}
		final Authors findByName = authorRepository.findByName(author.getName());
		if (findByName != null) {
			throw new BookStoreServiceException(ErrorMessages.AUTHOR_ALREADY_EXISTS);
		}
		return authorRepository.save(author);
	}

	@Override
	public void deleteAuthorById(Long authorId) throws BookStoreServiceException {
		if (authorId == null) {
			throw new BookStoreServiceException(ErrorMessages.NULL_VALUE);
		}
		if (authorRepository.findById(authorId) == null) {
			throw new BookStoreServiceException(ErrorMessages.AUTHOR_NOT_FOUND);
		}
		authorRepository.deleteById(authorId);
	}

}
