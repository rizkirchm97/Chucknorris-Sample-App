package com.rizkir.chucknorrissampleapp.di

import com.rizkir.chucknorrissampleapp.common.Constans
import com.rizkir.chucknorrissampleapp.data.remote.ChucknorrisApi
import com.rizkir.chucknorrissampleapp.data.repository.JokeRepositoryImpl
import com.rizkir.chucknorrissampleapp.domain.repository.JokeRepository
import com.rizkir.chucknorrissampleapp.domain.usecase.GetJokesUseCase
import com.rizkir.chucknorrissampleapp.presentation.main.MainViewModel
import com.rizkir.chucknorrissampleapp.presentation.main.component.MainAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideChucknorrisApi() : ChucknorrisApi {
        return Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ChucknorrisApi::class.java)
    }

    @Provides
    fun provideOkhttpClient(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.interceptors().add(Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            response
        })

        return httpClient.addInterceptor(interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideJokeRepositoryImpl(
        api: ChucknorrisApi
    ): JokeRepository {
        return JokeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetJokesUseCase(
        repository: JokeRepository
    ) : GetJokesUseCase {
        return GetJokesUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideMainAdapter() = MainAdapter()
}