package co.veo.veotest.ui

import kotlinx.serialization.Serializable

@Serializable
object ListDest

@Serializable
data class DetailsDest(val movieId: Int)