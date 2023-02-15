package com.sk.demo.service

import com.sk.demo.api.SKResponse
import com.sk.demo.api.SKRequest
import com.sk.demo.repository.SKExample
import com.sk.demo.repository.SuperkassaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SKService (val repository: SuperkassaRepository) {

    @Synchronized
    fun modifySKExample(request: SKRequest): SKResponse =
        SKResponse(current = repository
            .findByIdOrNull(request.id)
            ?.apply { this.obj.current = this.obj.current.plus(request.add) }
            ?.let { saveSKExample(it).obj.current }
            ?: throw IllegalArgumentException()
        )

    @Transactional
    fun saveSKExample(skExample: SKExample) = repository.save(skExample)
}
