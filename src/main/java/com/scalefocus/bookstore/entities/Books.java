package com.scalefocus.bookstore.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "books")
public class Books implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "T_BOOK_ID")
	private Long id;

	@NotNull
	@Column(name = "T_BOOK_ISBN", nullable = false)
	private Long isbn;

	@NotNull
	@Column(name = "T_BOOK_NAME", nullable = false, length = 150)
	private String name;
	

	@Column(name = "T_BOOK_DESC", nullable = false, length = 250)
	private String description;

	@ManyToOne
	@JoinColumn(name = "T_BOOK_AUTHOR_ID")
	private Authors author;


	public Books() {
	}

	public Books(Long id, Long isbn, String name, Authors author, String description) {
		this();
		this.id = id;
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.description = description;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!(object instanceof Books)) {
			return false;
		}

		final Books book = (Books) object;
//		return book.getId() == id //
//			&& isbn == book.getIsbn()//
//				&& book.getName().equals(name) //
//				&& book.getDescription().equals(description)//
//				&& book.getAuthor().equals(author);

		return id == book.getId()//
				&& isbn == book.getIsbn()//
				&& Objects.equals(name, book.getName())//
				&& Objects.equals(description, book.getDescription()) //
				&& Objects.equals(author, book.getAuthor());
	}

	@Override
	public int hashCode() {
		int hash = 17;
		final int idx = 31;
		hash = (int) (idx * hash + id);
		hash = idx * hash + (name == null ? 0 : name.hashCode());
		hash = idx * hash + (description == null ? 0 : description.hashCode());
		hash = idx * hash + (author == null ? 0 : author.hashCode());
		return hash;
	}

	@Override
	public String toString() {
		return "Book's id: " + id + ", "//
				+ "Book's ISBN: " + isbn + ", "//
				+ "Book's name: " + this.name + ", " //
				+ "Book's author: " + this.author.getName() + " , " //
				+ "Book's description: " + this.description;
	}

}
//@Transient
//private List<String> strings = Arrays.asList(new String[] { "a", "b", "c" });
