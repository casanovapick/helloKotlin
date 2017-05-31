package com.example.picked.hellokotlin.service

import com.example.picked.hellokotlin.retrofit.createRetrofitService
import dagger.Module
import dagger.Provides

@Module
class MovieServiceModule {
    @Provides fun provideMovieService() = createRetrofitService(MovieService::class.java)
}