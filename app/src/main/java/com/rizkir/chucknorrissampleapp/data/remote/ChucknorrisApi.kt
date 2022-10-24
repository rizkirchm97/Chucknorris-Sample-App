package com.rizkir.chucknorrissampleapp.data.remote

import com.rizkir.chucknorrissampleapp.data.remote.dto.ApiResponse
import com.rizkir.chucknorrissampleapp.data.remote.dto.JokeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChucknorrisApi {

    @GET("jokes/search")
    suspend fun getJokeByQuery(
        @Query("query") query: String
    ) : ApiResponse

}