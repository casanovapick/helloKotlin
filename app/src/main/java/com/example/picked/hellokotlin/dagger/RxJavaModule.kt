package com.example.picked.hellokotlin.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
@Module
class RxJavaModule {
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()
}