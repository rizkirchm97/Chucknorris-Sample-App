package com.rizkir.chucknorrissampleapp.presentation.main.component

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.rizkir.chucknorrissampleapp.R
import com.rizkir.chucknorrissampleapp.databinding.ItemJokeBinding
import com.rizkir.chucknorrissampleapp.domain.model.Joke
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter() : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var jokes = ArrayList<Joke>()

    @SuppressLint("NotifyDataSetChanged")
    fun setJokes(jokes: List<Joke>) {
        this.jokes = jokes as ArrayList<Joke>
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearJokes() {
        val size = this.jokes.size
        jokes.clear()
        notifyDataSetChanged()
//        val newList = jokes.toMutableList()
//        newList.remove(newList)
//        notifyDataSetChanged()
//        setJokes(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val joke = jokes[position]
        holder.bind(joke)
    }

    override fun getItemCount(): Int = jokes.size

    inner class MainViewHolder(private val binding: ItemJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NewApi")
        fun bind(joke: Joke) {
            with(binding) {
                jokeText.text = joke.value
                createdAtText.text = joke.created_at.format(
                    DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm",
                        Locale.getDefault()
                    )
                )
            }
        }
    }
}