package com.example.picked.hellokotlin.ui.movie

import com.example.picked.hellokotlin.data.Movie
import com.example.picked.hellokotlin.service.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListPresenter @Inject constructor(val movieService: MovieService
                                             , val view: MovieListContract.View
                                             , val movieList: MutableList<Movie>
                                             , val compositeDisposable: CompositeDisposable)
    : MovieListContract.Action {

    override fun start() = reLoadContent()

    private fun reLoadContent() {
        view.displayContent()
        movieList.clear()
        view.dismissProgress()
        val subscribe = movieService.getMovieList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally { view.dismissProgress() }
                .subscribeBy(
                        onNext = {
                            movieList.addAll(it)
                            view.updateList()
                        }
                        ,
                        onError = {
                            view.displayError()
                        }
                )
        compositeDisposable.add(subscribe)
    }

    override fun close() = compositeDisposable.dispose()

}