package moe.nikolay.chucknorisjokes.data

import retrofit2.Retrofit
import retrofit2.http.*
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory

interface ChuckNorrisService {

    @GET("jokes/random/{count}")
    fun randomJoke(
        @Path("count") count: Int
    ): Call<ChuckNorrisModel>


    companion object {
        fun create(): ChuckNorrisService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://api.icndb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ChuckNorrisService::class.java)
        }
    }

}