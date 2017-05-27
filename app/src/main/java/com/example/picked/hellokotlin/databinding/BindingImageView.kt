package com.example.picked.hellokotlin.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("bind:imageUrl")
fun bindImageUrl(imageView: ImageView?, imageUrl: String) {
    Glide.with(imageView).load(imageUrl).into(imageView)
}