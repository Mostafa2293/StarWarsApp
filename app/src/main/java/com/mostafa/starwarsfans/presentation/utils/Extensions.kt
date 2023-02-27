package com.mostafa.starwarsfans.presentation.utils

import android.view.View


const val CLICK_PERIOD = 500
fun View.setSafeClickListener(clickListener: View.OnClickListener) {
    var lastClickTime = 0L
    this.setOnClickListener {
        if (System.currentTimeMillis() - lastClickTime < CLICK_PERIOD) return@setOnClickListener
        lastClickTime = System.currentTimeMillis()
        clickListener.onClick(this)
    }
}