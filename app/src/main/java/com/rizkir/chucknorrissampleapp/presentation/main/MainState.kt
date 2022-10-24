package com.rizkir.chucknorrissampleapp.presentation.main

import com.rizkir.chucknorrissampleapp.domain.model.Joke

data class MainState(
    val isLoading: Boolean = false,
    val loadingMessage: String? = "",
    val isError: Boolean = false,
    val errorMessage: String = "",
    val jokes: List<Joke>? = emptyList()
)
