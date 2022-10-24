package com.rizkir.chucknorrissampleapp.domain.usecase

import android.annotation.SuppressLint
import com.rizkir.chucknorrissampleapp.common.Resource
import com.rizkir.chucknorrissampleapp.domain.model.Joke
import com.rizkir.chucknorrissampleapp.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Business Rules for GetJokesUseCase
// should place here
// This class shouldn't know anything about the Data
class GetJokesUseCase @Inject constructor(
    private val jokeRepository: JokeRepository
) {
    @SuppressLint("NewApi")
    suspend operator fun invoke(
        query: String
    ): Flow<Resource<List<Joke>?>> = jokeRepository.getJokeBySearch(query)
}
