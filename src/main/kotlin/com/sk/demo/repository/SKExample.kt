package com.sk.demo.repository

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Type

@Entity
@Table(name = "sk_example_table")
class SKExample(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Int? = null,

    @Column(columnDefinition = "jsonb")
    @Type(JsonBinaryType::class)
    var obj: CurrentNode
)
