package com.example.model.entity

import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.GenerationType
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.ManyToOne
import org.babyfish.jimmer.sql.Table
import java.math.BigDecimal

@Entity
@Table(name = "book")
interface Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long

	@Key
	val title: String

	val price: BigDecimal

	@ManyToOne
	val author: Author
}
