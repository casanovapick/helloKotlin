package com.example.picked.hellokotlin.ui.movie

interface MovieListContract {
    interface View {
        fun updateList()
    }

    interface Action {
        fun start()
        fun close()
    }
}