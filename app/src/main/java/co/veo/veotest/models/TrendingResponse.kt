package co.veo.veotest.models

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @SerializedName("results")
    val results: List<MovieResponse>
) {
    data class MovieResponse(
        @SerializedName("original_name")
        val originalTitle: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("poster_path")
        val posterPath: String
    )
}