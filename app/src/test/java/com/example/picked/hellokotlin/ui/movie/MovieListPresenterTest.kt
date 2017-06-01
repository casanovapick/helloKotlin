package com.example.picked.hellokotlin.ui.movie

import com.example.picked.hellokotlin.data.Movie
import com.example.picked.hellokotlin.helper.RxSchedulersOverrideSpek
import com.example.picked.hellokotlin.service.MovieService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.include
import org.mockito.Mockito.`when`
import java.io.IOException

class MovieListPresenterTest : Spek({
    include(RxSchedulersOverrideSpek)
    val movieService: MovieService = mock()
    val view: MovieListContract.View = mock()
    var movieList: MutableList<Movie> = mutableListOf()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val presenter: MovieListPresenter = MovieListPresenter(movieService, view, movieList, compositeDisposable)

    given("getMovieList response success") {
        beforeEachTest {
            movieList.add(Movie())
            val movieListObservable: Observable<List<Movie>> = Observable.just(movieList)
            `when`(movieService.getMovieList()).thenReturn(movieListObservable)
        }
        on("start") {
            presenter.start()
            it("should updateList") {
                verify(view).updateList()
            }
        }
    }

    given("getMovieList no internet connection") {
        beforeEachTest {
            movieList.add(Movie())
            val movieListObservable: Observable<List<Movie>> = Observable.error { IOException() }
            `when`(movieService.getMovieList()).thenReturn(movieListObservable)
        }
        on("start") {
            presenter.start()
            it("should display error") {
                verify(view).displayError()
            }
        }
    }

})