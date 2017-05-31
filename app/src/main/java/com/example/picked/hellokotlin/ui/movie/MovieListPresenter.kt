package com.example.picked.hellokotlin.ui.movie

import com.example.picked.hellokotlin.data.Movie
import com.example.picked.hellokotlin.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListPresenter : MovieListContract.Action {
    @Inject lateinit var movieService: MovieService
    @Inject lateinit var view: MovieListContract.View
    @Inject lateinit var movieList: MutableList<Movie>
    @Inject lateinit var compositeDisposable: CompositeDisposable

    @Inject
    constructor(movieService: MovieService, view: MovieListContract.View, movieList: MutableList<Movie>, compositeDisposable: CompositeDisposable) {
        this.movieService = movieService
        this.view = view
        this.movieList = movieList
        this.compositeDisposable = compositeDisposable
    }


    override fun start() {
        val subscribe = movieService.getMovieList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    movieList.addAll(it)
                    view.updateList()
                }.subscribe()
        compositeDisposable.add(subscribe)
    }

    override fun close() {
        compositeDisposable.dispose()
    }

}