package com.example.picked.hellokotlin.ui.movie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife

import com.example.picked.hellokotlin.R
import com.example.picked.hellokotlin.data.Movie
import com.example.picked.hellokotlin.retrofit.createRetrofitService
import com.example.picked.hellokotlin.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieListActivity : AppCompatActivity() {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    private var list = ArrayList<Movie>();

    private val movieAdapter = MovieAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        ButterKnife.bind(this)
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val service = createRetrofitService(MovieService::class.java)
        service.getMovieList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).doOnNext {
            list.addAll(it)
            movieAdapter.notifyDataSetChanged()
        }.subscribe()
    }
}
