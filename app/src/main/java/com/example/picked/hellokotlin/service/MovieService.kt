package com.example.picked.hellokotlin.service

import com.example.picked.hellokotlin.data.Movie
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieService {
    @GET("movies.json")
    fun getMovieList(): Observable<List<Movie>>
}