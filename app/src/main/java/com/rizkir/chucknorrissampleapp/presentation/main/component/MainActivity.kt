package com.rizkir.chucknorrissampleapp.presentation.main.component

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.rizkir.chucknorrissampleapp.R
import com.rizkir.chucknorrissampleapp.databinding.ActivityMainBinding
import com.rizkir.chucknorrissampleapp.presentation.main.MainEvent
import com.rizkir.chucknorrissampleapp.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainAdapter: MainAdapter

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupRv()
        observeViewModel()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        binding.searchView.setIconifiedByDefault(false)
        val closeBtn = binding.searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
//        val searchBtn = binding.searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_button)

        binding.searchView.isSubmitButtonEnabled = true
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mainViewModel.onEvent(MainEvent.OnSearchQueryChanged(it))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        closeBtn.setOnClickListener {
            binding.searchView.setQuery("", false)
            binding.searchView.clearFocus()
            mainViewModel.onEvent(MainEvent.OnSearchQueryChanged(""))
            mainAdapter.clearJokes()
        }

    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.state.collectLatest{ state ->
                if (state.isLoading) {
                    binding.shimmerViewContainer.startShimmer()
                    binding.jokesRecyclerView.visibility = View.GONE
                    binding.noResultsMessageTex.visibility = View.GONE

                }
                if (state.isError) {
                    binding.jokesRecyclerView.visibility = View.GONE
                    binding.noResultsMessageTex.visibility = View.VISIBLE
                    binding.noResultsMessageTex.text = state.errorMessage
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE

                }
                if (state.jokes?.isNotEmpty() == true) {
                    binding.noResultsMessageTex.visibility = View.GONE
                    binding.jokesRecyclerView.visibility = View.VISIBLE
                    mainAdapter.setJokes(state.jokes)
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE

                }
                if (state.jokes?.isEmpty() == true) {
                    binding.jokesRecyclerView.visibility = View.GONE
                    binding.noResultsMessageTex.visibility = View.VISIBLE
                    binding.noResultsMessageTex.text = getString(R.string.no_data)
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE

                }
            }
        }

    }

    private fun setupRv() {
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.jokesRecyclerView.layoutManager = layoutManager
        binding.jokesRecyclerView.adapter = mainAdapter
    }
}