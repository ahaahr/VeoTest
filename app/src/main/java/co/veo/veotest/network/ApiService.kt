package co.veo.veotest.network

import co.veo.veotest.models.DetailsResponse
import co.veo.veotest.models.TrendingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

private const val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NTcwYmM0MjgxY2RkZDdlNTNiNzYxY2ZlYjg0OTIyNyIsInN1YiI6IjY2NWRiOWQ1YTNmYTViYWM3MDQ5MWVlZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.kyfNUr5KGnjwb-1j5oTN1637XR0raGTixdo_4IpZ-u8"

interface ApiService {

    @GET("trending/all/week")
    fun getTrending(
        @Header("accept") accept: String = "application/json",
        @Header("Authorization") authToken: String = token
    ): Call<TrendingResponse>

    @GET("movie/{movieId}")
    fun getDetails(
        @Header("accept") accept: String = "application/json",
        @Header("Authorization") authToken: String = token,
        @Path("movieId") movieId: String
    ): Call<DetailsResponse>
}