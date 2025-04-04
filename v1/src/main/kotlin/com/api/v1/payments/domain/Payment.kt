package com.api.v1.payments.domain

import com.api.v1.cards.domain.Card
import com.api.v1.medical_appointments.domain.exposed.MedicalAppointment
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

@Document
data class Payment (
    @Id
    val id: String,
    val medicalAppointment: MedicalAppointment,
    val card: Card,
    val createdAt: LocalDateTime
) {

    companion object {
        fun of(medicalAppointment: MedicalAppointment, card: Card): Payment {
            return Payment(
                UUID.randomUUID().toString(),
                medicalAppointment,
                card,
                LocalDateTime.now()
            )
        }
    }
}