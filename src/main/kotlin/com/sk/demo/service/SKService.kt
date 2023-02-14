package com.sk.demo.service

import com.sk.demo.api.SKResponse
import com.sk.demo.api.SKRequest
import com.sk.demo.repository.CurrentNode
import com.sk.demo.repository.SKExample
import com.sk.demo.repository.SuperkassaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SKService (val repository: SuperkassaRepository) {

    @Synchronized
    fun modifySKExample(request: SKRequest): SKResponse {
        val skExample = repository
            .findByIdOrNull(request.id)
            ?.apply { addDelta(request) }
            ?: SKExample(obj = CurrentNode(request.add))

        return SKResponse(
            current = saveSKExample(skExample).obj.current
        )
    }

    private fun SKExample.addDelta(request: SKRequest) = this.obj.current.plus(request.add).also { this.obj.current = it }

    @Transactional
    fun saveSKExample(skExample: SKExample) = repository.save(skExample)
}
