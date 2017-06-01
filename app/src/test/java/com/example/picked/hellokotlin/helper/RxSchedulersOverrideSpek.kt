package com.example.picked.hellokotlin.helper


import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.jetbrains.spek.api.Spek


object RxSchedulersOverrideSpek : Spek({

    beforeGroup {
        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler { _->Schedulers.trampoline() }
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _->Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { _->Schedulers.trampoline() }
    }
    afterGroup {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

})