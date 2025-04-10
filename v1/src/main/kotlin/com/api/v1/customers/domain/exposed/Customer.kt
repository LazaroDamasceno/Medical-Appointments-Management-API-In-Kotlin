package com.api.v1.customers.domain.exposed

import com.api.v1.common.Address
import com.api.v1.customers.dtos.exposed.CustomerResponseDto
import com.api.v1.people.domain.exposed.Person
import com.api.v1.people.utils.formatFullName
import org.bson.codecs.pojo.annotations.BsonId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document
class Customer(
    var person: Person,
    var address: Address
) {

    @BsonId
    var id: String = UUID.randomUUID().toString()
    var createdAt: LocalDateTime = LocalDateTime.now()

    companion object {
        fun of(person: Person, address: Address): Customer {
            return Customer(person, address)
        }
    }

    fun toDto(): CustomerResponseDto {
        return CustomerResponseDto(
            id,
            person.formatFullName()
        )
    }
}