package com.scalefocus.bookstore.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scalefocus.bookstore.entities.Authors;
import com.scalefocus.bookstore.entities.Books;
import com.scalefocus.bookstore.repositories.AuthorRepository;
import com.scalefocus.bookstore.repositories.BooksRepository;

@Configuration
public class AppConfig {

	@Bean
	public CommandLineRunner commandLineRunner(AuthorRepository authorRepository, BooksRepository booksRepository) {
		
		Authors author = new Authors();
		author.setName("Jane Austen");
		author.setDescription("English novelist ");

		authorRepository.save(author);

		Authors author1 = new Authors();
		author1.setName("John Steinbeck");
		author1.setDescription(
				"John Steinbeck (1902-1968), winner of the Nobel Prize in Literature, achieved popular success in 1935 when he published Tortilla Flat. ");
		authorRepository.save(author1);

		final Books book = new Books();
		book.setName("Pride and Prejudice");
		book.setAuthor(author);
		book.setDescription(
				"the most famous of Jane Austen's novels, and its opening is one of the most famous lines in English literature");
		booksRepository.save(book);

		final Books book1 = new Books();
		book1.setName("Sense and Sensibility");
		book1.setAuthor(author);
		book1.setDescription("the first of Austen's novels to be published, under the pseudonym \"A Lady\".");
		booksRepository.save(book1);

		final Books book2 = new Books();
		book2.setName("Mansfield Park");
		book2.setAuthor(author);
		book2.setDescription(
				"he novel was written between 1812 and 1814 at Chawton Cottage, and published in July 1814 by the same Mr. Egerton");
		booksRepository.save(book2);
		
		final Books book3 = new Books();
		book3.setName("East of Eden ");
		book3.setAuthor(author1);
		book3.setDescription(
				"East of Eden is a novel by Nobel Prize winner John Steinbeck, published in September 1952");
		booksRepository.save(book3);

		return args -> {
			authorRepository.findAll().forEach(a -> System.out.println(a.toString()));
		};
	}

}
