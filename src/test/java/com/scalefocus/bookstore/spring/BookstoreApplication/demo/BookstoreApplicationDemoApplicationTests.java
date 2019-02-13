package com.scalefocus.bookstore.spring.BookstoreApplication.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BookstoreApplicationDemoApplicationTests {

	// @Autowired BooksService

	// @MockBean BooksRepository

	@Test
	public void contextLoads() {
	}

	@Test
	public void getAllBooks() {

		// String URI
		// Check form accepting JSON value and returning status 200 - MvcResult?
		// Check response content
		// Assert that BooksList.length >0;
	}

	@Test
	public void getBookById() {
		// String URI
		// Check form accepting JSON value and returning status 200 - MvcResult?
		// Check response content
		// Assert that BooksList.length == 1
	}

}
