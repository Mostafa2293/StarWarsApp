package com.mostafa.starwarsfans.presentation.utils


import android.util.Log
import android.view.View

import androidx.databinding.BindingAdapter

@BindingAdapter("safeClick")
fun safeClick(view: View, listener: View.OnClickListener) {
    Log.d("button", "safeClick: clickeddddddddddd")
    view.setSafeClickListener(listener)
}
