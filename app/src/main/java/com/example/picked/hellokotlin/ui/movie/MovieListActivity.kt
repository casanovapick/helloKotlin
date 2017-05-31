package com.example.picked.hellokotlin.ui.movie

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.picked.hellokotlin.R
import com.example.picked.hellokotlin.data.Movie
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView
    private var list = ArrayList<Movie>();
    val movieAdapter = MovieAdapter(list)
    @Inject lateinit var presenter:MovieListPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        ButterKnife.bind(this)
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        DaggerMovieListComponent.builder()
                .movieListPresenterModule(MovieListPresenterModule(this, list))
                .build()
                .inject(this)
        presenter.start()
    }

    override fun updateList() {
        movieAdapter.notifyDataSetChanged()
    }
}
