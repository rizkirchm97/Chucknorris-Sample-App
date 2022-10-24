package com.rizkir.chucknorrissampleapp.data.remote.dto

import com.squareup.moshi.Json

/**
 * created by RIZKI RACHMANUDIN on 22/10/2022
 */
data class ApiResponse(
    @field:Json(name = "total") val total: Int?,
    @field:Json(name = "result") val result: List<JokeDto>?
)
