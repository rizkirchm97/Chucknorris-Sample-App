package com.rizkir.chucknorrissampleapp.presentation.main

/**
 * created by RIZKI RACHMANUDIN on 23/10/2022
 */
sealed class MainEvent {
    data class OnSearchQueryChanged(val query: String) : MainEvent()
}

