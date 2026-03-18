package com.example.service

import com.example.model.entity.Author
import org.babyfish.jimmer.View
import kotlin.reflect.KClass

interface AuthorService {

	fun <V : View<Author>> findAll(viewType: KClass<V>): List<V>

	fun <V : View<Author>> findById(id: Long, viewType: KClass<V>): V?
}
