package com.rizkir.chucknorrissampleapp.data.repository

import android.annotation.SuppressLint
import com.rizkir.chucknorrissampleapp.common.Resource
import com.rizkir.chucknorrissampleapp.data.mapper.toJoke
import com.rizkir.chucknorrissampleapp.data.remote.ChucknorrisApi
import com.rizkir.chucknorrissampleapp.data.remote.dto.JokeDto
import com.rizkir.chucknorrissampleapp.domain.model.Joke
import com.rizkir.chucknorrissampleapp.domain.repository.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeRepositoryImpl @Inject constructor(
    val api: ChucknorrisApi
): JokeRepository {
    @SuppressLint("NewApi")
    override suspend fun getJokeBySearch(query: String): Flow<Resource<List<Joke>?>> = flow {
        emit(Resource.Loading(true))
        try {
            val jokes = api.getJokeByQuery(query).result?.map { it.toJoke() }
            emit(Resource.Success(jokes))
            emit(Resource.Loading(false))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
            emit(Resource.Loading(false))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
            emit(Resource.Loading(false))
        }
    }.flowOn(Dispatchers.IO)

}
