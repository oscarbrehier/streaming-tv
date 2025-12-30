package com.example.firestream

import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import coil.load
import coil.size.Scale

class PosterCardPresenter: Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(300, 450)
            scaleType = ImageView.ScaleType.CENTER_CROP
            isFocusable = true
            isFocusableInTouchMode = true
        }
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val movie = item as? Movie ?: return
        val imageView = viewHolder.view as ImageView

        val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
        imageView.load(posterUrl) {
            crossfade(true)
            scale(Scale.FILL)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}

}
