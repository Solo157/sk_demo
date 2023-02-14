package com.sk.demo.repository

import org.springframework.data.repository.CrudRepository

interface SuperkassaRepository : CrudRepository<SKExample, Int>
