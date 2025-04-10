package com.api.v1.people.utils

import com.api.v1.people.domain.exposed.Person

fun Person.formatFullName(): String {
    if (middleName.isNullOrBlank()) {
        return "$firstName $lastName"
    }
    return "$firstName $middleName $lastName"
}