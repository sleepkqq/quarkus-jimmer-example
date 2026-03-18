package com.example.model.repository

import com.example.model.entity.Author
import io.quarkiverse.jimmer.runtime.repository.KRepository

interface AuthorRepository : KRepository<Author, Long>
