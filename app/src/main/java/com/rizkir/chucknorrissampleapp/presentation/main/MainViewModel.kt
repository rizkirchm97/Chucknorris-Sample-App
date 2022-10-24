package com.rizkir.chucknorrissampleapp.presentation.main

import androidx.lifecycle.*
import com.rizkir.chucknorrissampleapp.common.Resource
import com.rizkir.chucknorrissampleapp.domain.usecase.GetJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getJokesUserCase: GetJokesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState>
    get() = _state.asStateFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnSearchQueryChanged -> {
                getJokes(event.query)
            }
        }
    }

    fun getJokes(query: String) {
        viewModelScope.launch {
            getJokesUserCase(query).collect() { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            jokes = result.data,
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isError = true,
                            errorMessage = result.message ?: "An unknown error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = result.isLoading, loadingMessage = result.message)
                    }
                }
            }
        }

    }
}