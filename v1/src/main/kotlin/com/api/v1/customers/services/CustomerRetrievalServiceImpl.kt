package com.api.v1.customers.services

import com.api.v1.customers.domain.CustomerRepository
import com.api.v1.customers.dtos.exposed.CustomerResponseDto
import com.api.v1.customers.utils.CustomerFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CustomerRetrievalServiceImpl(
    private val customerRepository: CustomerRepository,
    private val customerFinder: CustomerFinder
) : CustomerRetrievalService {

    override suspend fun findAll(): ResponseEntity<Flow<CustomerResponseDto>> {
        return withContext(Dispatchers.IO) {
            val response = customerRepository
                .findAll()
                .map { c -> c.toDto() }
            ResponseEntity.ok(response)
        }
    }

    override suspend fun findById(id: String): ResponseEntity<CustomerResponseDto> {
        return withContext(Dispatchers.IO) {
            val response = customerFinder.findById(id).toDto()
            ResponseEntity.ok(response)
        }
    }
}