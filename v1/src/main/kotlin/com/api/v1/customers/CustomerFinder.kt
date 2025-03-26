package com.api.v1.customers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class CustomerFinder {

    private final val customerRepository: CustomerRepository

    constructor(customerRepository: CustomerRepository) {
        this.customerRepository = customerRepository
    }

    suspend fun findById(id: String): Customer {
        return withContext(Dispatchers.IO) {
            val customer = customerRepository
                .findById(id)
            if (customer == null) {
                throw NonExistentCustomerException(id)
            }
            customer
        }
    }

}