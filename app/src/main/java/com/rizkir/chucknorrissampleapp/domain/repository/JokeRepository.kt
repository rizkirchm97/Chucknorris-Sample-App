package com.rizkir.chucknorrissampleapp.domain.repository

import com.rizkir.chucknorrissampleapp.common.Resource
import com.rizkir.chucknorrissampleapp.data.remote.dto.JokeDto
import com.rizkir.chucknorrissampleapp.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepository {

    suspend fun getJokeBySearch(
        query: String
    ): Flow<Resource<List<Joke>?>>
}