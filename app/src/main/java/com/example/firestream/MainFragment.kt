package com.example.firestream

import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.firestream.BuildConfig

class MainFragment : BrowseSupportFragment() {

    private val apiKey = BuildConfig.TMDB_API_KEY

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        headersState = HEADERS_DISABLED
        title = null
        brandColor = 0x00000000
        searchAffordanceColor = 0x00000000

        super.onActivityCreated(savedInstanceState)

        loadMovies()
    }

    private fun loadMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val api = TMDBApi.create()
                val response = withContext(Dispatchers.IO) {
                    api.getPopularMovies(apiKey)
                }

                val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
                val rowAdapter = ArrayObjectAdapter(PosterCardPresenter())
                val header = HeaderItem(0, "Popular Movies")

                response.results.forEach { movie ->
                    rowAdapter.add(movie)
                }

                rowsAdapter.add(ListRow(header, rowAdapter))
                adapter = rowsAdapter

                setSelectedPosition(0, false)

            } catch (e: Exception) {
                showError()
            }
        }
    }

    private fun showError() {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val rowAdapter = ArrayObjectAdapter(SimpleCardPresenter())
        val header = HeaderItem(0, "Unable to load")

        rowAdapter.add("Check your internet connection")
        rowsAdapter.add(ListRow(header, rowAdapter))
        adapter = rowsAdapter

        setSelectedPosition(0, false)
    }
}
