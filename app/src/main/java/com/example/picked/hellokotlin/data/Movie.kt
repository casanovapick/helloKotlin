package com.example.picked.hellokotlin.data

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@javax.annotation.Generated("com.robohorse.robopojogenerator")
data class Movie(

        @field:SerializedName("image")
        val image: String? = null,

        @field:SerializedName("rating")
        val rating: Float? = null,

        @field:SerializedName("genre")
        val genre: List<String?>? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("releaseYear")
        val releaseYear: Int? = null
)

