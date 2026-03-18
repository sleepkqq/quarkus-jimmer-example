package com.example.service

import com.example.model.entity.Book
import com.example.model.entity.dto.BookInput
import org.babyfish.jimmer.View
import kotlin.reflect.KClass

interface BookService {

	fun <V : View<Book>> findAll(viewType: KClass<V>): List<V>

	fun <V : View<Book>> findById(id: Long, viewType: KClass<V>): V?

	fun <V : View<Book>> create(input: BookInput, viewType: KClass<V>): V

	fun <V : View<Book>> update(id: Long, input: BookInput, viewType: KClass<V>): V

	fun deleteById(id: Long)
}
