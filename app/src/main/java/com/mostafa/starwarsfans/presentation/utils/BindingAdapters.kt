package com.mostafa.starwarsfans.presentation.utils


import android.view.View

import androidx.databinding.BindingAdapter

@BindingAdapter("safeClick")
fun safeClick(view: View, listener: View.OnClickListener) {
    view.setSafeClickListener(listener)
}
