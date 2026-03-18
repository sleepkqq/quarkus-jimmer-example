package com.example.service.impl

import com.example.model.entity.Author
import com.example.model.repository.AuthorRepository
import com.example.service.AuthorService
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.babyfish.jimmer.View
import kotlin.reflect.KClass

@ApplicationScoped
class AuthorServiceImpl(
	private val authorRepository: AuthorRepository
) : AuthorService {

	@Transactional(Transactional.TxType.SUPPORTS)
	override fun <V : View<Author>> findAll(viewType: KClass<V>): List<V> =
		authorRepository.viewer(viewType).findAll()

	@Transactional(Transactional.TxType.SUPPORTS)
	override fun <V : View<Author>> findById(id: Long, viewType: KClass<V>): V? =
		authorRepository.viewer(viewType).findNullable(id)
}
