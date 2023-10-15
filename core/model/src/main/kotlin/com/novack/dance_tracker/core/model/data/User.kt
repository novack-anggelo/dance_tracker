package com.novack.dance_tracker.core.model.data

import kotlinx.datetime.Instant

data class User(
    val name: String,
    val lastName: String,
    val dob: Instant,
    val gender: Gender
)

sealed interface Gender {
    data object MALE : Gender
    data object FEMALE : Gender
}
