package com.example.model.entity

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.GenerationType
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.OneToMany
import org.babyfish.jimmer.sql.Table

@Entity
@Table(name = "author")
interface Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long

	@Key
	val name: String

	@OneToMany(mappedBy = "author")
	val books: List<Book>
}
