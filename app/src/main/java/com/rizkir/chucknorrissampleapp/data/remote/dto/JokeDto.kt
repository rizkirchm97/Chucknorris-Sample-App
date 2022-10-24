package com.rizkir.chucknorrissampleapp.data.remote.dto

import com.squareup.moshi.Json

data class JokeDto(
    @field:Json(name = "categories") val categories: List<String>?,
    @field:Json(name = "created_at") val created_at: String?,
    @field:Json(name = "icon_url") val icon_url: String?,
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "updated_at") val updated_at: String?,
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "value") val value: String?
)
