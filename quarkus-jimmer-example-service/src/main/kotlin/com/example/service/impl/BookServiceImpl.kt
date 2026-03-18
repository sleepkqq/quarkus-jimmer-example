package com.example.service.impl

import com.example.model.entity.Book
import com.example.model.entity.dto.BookInput
import com.example.model.repository.BookRepository
import com.example.service.BookService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.babyfish.jimmer.View
import kotlin.reflect.KClass

@ApplicationScoped
class BookServiceImpl(
	private val bookRepository: BookRepository
) : BookService {

	@Transactional(Transactional.TxType.SUPPORTS)
	override fun <V : View<Book>> findAll(viewType: KClass<V>): List<V> =
		bookRepository.viewer(viewType).findAll()

	@Transactional(Transactional.TxType.SUPPORTS)
	override fun <V : View<Book>> findById(id: Long, viewType: KClass<V>): V? =
		bookRepository.viewer(viewType).findNullable(id)

	@Transactional
	override fun <V : View<Book>> create(input: BookInput, viewType: KClass<V>): V =
		bookRepository.saveCommand(input)
			.execute(viewType)
			.modifiedView

	@Transactional
	override fun <V : View<Book>> update(id: Long, input: BookInput, viewType: KClass<V>): V =
		bookRepository.saveCommand(input.toEntity { this.id = id })
			.execute(viewType)
			.modifiedView

	@Transactional
	override fun deleteById(id: Long) {
		bookRepository.deleteById(id)
	}
}
