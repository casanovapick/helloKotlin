package com.example.picked.hellokotlin.ui.movie

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.picked.hellokotlin.BR
import com.example.picked.hellokotlin.R
import com.example.picked.hellokotlin.data.Movie

/**
 * Created by Picked on 5/28/2017.
 */
class MovieAdapter(val movieList:List<Movie>) : RecyclerView.Adapter<BindingHolder>() {

    override fun onBindViewHolder(holder: BindingHolder?, position: Int) {
        holder?.binding?.setVariable(BR.movie,movieList[position])
        holder?.binding?.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BindingHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
        return BindingHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

}