package com.example.model.repository

import com.example.model.entity.Book
import io.quarkiverse.jimmer.runtime.repository.KRepository

interface BookRepository : KRepository<Book, Long>
