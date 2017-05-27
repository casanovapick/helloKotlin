package com.example.picked.hellokotlin.ui.movie

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Picked on 5/28/2017.
 */
class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding:ViewDataBinding = DataBindingUtil.bind(itemView.rootView)
}