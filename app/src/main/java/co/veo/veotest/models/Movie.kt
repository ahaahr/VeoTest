package co.veo.veotest.models

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String
)