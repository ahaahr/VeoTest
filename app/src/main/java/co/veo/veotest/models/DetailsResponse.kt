package co.veo.veotest.models

import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("original_name")
    val originalTitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: String,
    @SerializedName("overview")
    val overview: String
)