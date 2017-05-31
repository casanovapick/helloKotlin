package com.example.picked.hellokotlin.ui.movie

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.picked.hellokotlin.R
import com.example.picked.hellokotlin.data.Movie
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), MovieListContract.View {
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    @BindView(R.id.refreshLayout) lateinit var refreshLayout: SwipeRefreshLayout
    @BindView(R.id.errorLayout) lateinit var errorLayout: View
    private val list = ArrayList<Movie>();
    val movieAdapter = MovieAdapter(list)
    @Inject lateinit var presenter: MovieListPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        ButterKnife.bind(this)
        injectDependency()
        initRecyclerView()
        refreshLayout.setOnRefreshListener({ presenter.start() })
        presenter.start()
    }

    private fun injectDependency() {
        DaggerMovieListComponent.builder()
                .movieListPresenterModule(MovieListPresenterModule(this, list))
                .build()
                .inject(this)
    }

    private fun initRecyclerView() {
        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun updateList() {
        movieAdapter.notifyDataSetChanged()
    }

    override fun displayContent() {
        errorLayout.visibility = View.GONE
    }

    override fun displayError() {
        errorLayout.visibility = View.VISIBLE
    }

    @OnClick(R.id.errorRetryButton) fun onClickRetry() {
        presenter.start()
    }

    override fun displayProgress() {
        refreshLayout.post { refreshLayout.isRefreshing = true }
    }

    override fun dismissProgress() {
        refreshLayout.post { refreshLayout.isRefreshing = false }
    }


}
