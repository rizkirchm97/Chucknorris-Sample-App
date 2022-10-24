package com.rizkir.chucknorrissampleapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.rizkir.chucknorrissampleapp.data.remote.dto.JokeDto
import com.rizkir.chucknorrissampleapp.domain.model.Joke
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun JokeDto.toJoke(): Joke {
//    val pattern = "yyyy-MM-dd HH:mm"
//    val serverPattern = "yyyy-MM-dd HH:mm:ss.SSS"
//    val localDateTimeUpdate = LocalDateTime.parse(updated_at, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
//    val formatterUpdate = DateTimeFormatter.ofPattern(pattern)
//    val formattedUpdate = localDateTimeUpdate.format(formatterUpdate)
//
//    val localDateTime = LocalDateTime.parse(created_at, ofPattern(serverPattern))
//    val formatter = DateTimeFormatter.ofPattern(pattern)
//    val formattedCreate = localDateTime.format(formatter)


    return Joke(
        categories = categories ?: emptyList(),
        created_at = created_at ?: "",
        icon_url = icon_url ?: "",
        id = id ?: "",
        updated_at = updated_at ?: "",
        url = url ?: "",
        value = value ?: ""
    )
}