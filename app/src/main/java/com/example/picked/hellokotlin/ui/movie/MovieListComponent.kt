package com.example.picked.hellokotlin.ui.movie

import com.example.picked.hellokotlin.dagger.RxJavaModule
import com.example.picked.hellokotlin.service.MovieServiceModule
import dagger.Component

@Component(modules = arrayOf(MovieServiceModule::class,MovieListPresenterModule::class,RxJavaModule::class))
interface MovieListComponent {
    fun inject(activity: MovieListActivity)
}