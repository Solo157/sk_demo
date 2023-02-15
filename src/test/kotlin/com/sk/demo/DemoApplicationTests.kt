package com.sk.demo

import com.sk.demo.api.SKRequest
import com.sk.demo.repository.SKExample
import com.sk.demo.repository.SKObj
import com.sk.demo.repository.SuperkassaRepository
import com.sk.demo.service.SKService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch


@SpringBootTest
class DemoApplicationTests {

	@Autowired
	lateinit var service: SKService

	@Autowired
	lateinit var repository: SuperkassaRepository


	@BeforeEach
	fun initDB() {
		repository.save(SKExample(id = 1, obj = SKObj(0)))
	}

	@AfterEach
	fun resetDB() {
		repository.save(SKExample(id = 1, obj = SKObj(0)))
	}

	@Test
	fun skExample_currentValueIsZero_currentValueIncreasedForOneThousand() {
		val threadCount = 1000
		val latch = CountDownLatch(threadCount)

		for (i in 1..threadCount) {
			Thread {
				service.modifySKExample(SKRequest(id = 1, add = 1))
				latch.countDown()
			}.start()
		}
		latch.await()

		val current = repository.findById(1).get().obj.current
		assertEquals(threadCount, current)
	}
}
