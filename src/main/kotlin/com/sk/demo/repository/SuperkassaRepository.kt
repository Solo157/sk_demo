package com.sk.demo.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface SuperkassaRepository : CrudRepository<SKExample, Int> {

    @Modifying
    @Transactional
    @Query(value = "update sk_example_table set obj = cast(:obj as JSON)  where id = 1", nativeQuery = true)
    fun executeUpdate(obj: String)
}
