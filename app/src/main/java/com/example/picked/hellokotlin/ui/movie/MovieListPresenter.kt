package com.example.picked.hellokotlin.ui.movie

import com.example.picked.hellokotlin.data.Movie
import com.example.picked.hellokotlin.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListPresenter @Inject constructor(var movieService: MovieService
                                             , var view: MovieListContract.View
                                             , var movieList: MutableList<Movie>
                                             , var compositeDisposable: CompositeDisposable)
    : MovieListContract.Action {

    override fun start() = reLoadContent()

    private fun reLoadContent() {
        view.displayContent()
        movieList.clear()
        view.dismissProgress()
        val subscribe = movieService.getMovieList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    movieList.addAll(it)
                    view.updateList()
                }
                .doOnError { view.displayError() }
                .doFinally { view.dismissProgress() }
                .subscribe()
        compositeDisposable.add(subscribe)
    }

    override fun close() = compositeDisposable.dispose()

}