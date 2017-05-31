package com.example.picked.hellokotlin.ui.movie

interface MovieListContract {
    interface View {
        fun updateList()
        fun displayContent()
        fun displayError()
        fun displayProgress()
        fun dismissProgress();
    }

    interface Action {
        fun start()
        fun close()
    }
}