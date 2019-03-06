package com.scalefocus.bookstore.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "authors")
public class Authors implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "T_AUTHOR_ID", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "T_AUTHOR_NAME", nullable = false, length = 50 )
	private String name;

	@NotNull
	@Column(name = "T_AUTHOR_DESC", nullable = false, length = 250)
	private String description;
	
	@NotNull
	@Column(name = "T_AUTHOR_GENRE", nullable = false, length = 50)
	private String genre;

//	@OneToMany(mappedBy = "authors", fetch = FetchType.LAZY)
//	@JsonManagedReference
//	private Set<Books> books = new HashSet<Books>();

}
