package com.example.picked.hellokotlin.ui.movie

import com.example.picked.hellokotlin.data.Movie
import dagger.Module
import dagger.Provides

@Module
class MovieListPresenterModule(val view: MovieListContract.View, val movieList: MutableList<Movie>) {
    @Provides
    fun provideView(): MovieListContract.View = view

    @Provides
    fun provideMovieList(): MutableList<Movie> = movieList
}