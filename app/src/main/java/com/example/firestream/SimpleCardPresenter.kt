package com.example.firestream

import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter

class SimpleCardPresenter: Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {

        val textView = TextView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(300, 180)
            setBackgroundColor(Color.DKGRAY)
            setTextColor(Color.WHITE)
            textSize = 18f
            isFocusable = true
            isFocusableInTouchMode = true
            setPadding(20, 20, 20, 20 )
        }

        return ViewHolder(textView)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        (viewHolder.view as TextView).text = item.toString()
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}

}