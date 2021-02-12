package com.example.karat.utils

import android.view.View
import androidx.viewpager.widget.ViewPager


private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class FadeInPageTransformer  : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        if(position <= -1.0F || position >= 1.0F) {
            view.setAlpha(0.5F);
        } else if( position == 0.0F ) {
            view.setAlpha(1.0F);
        } else {
            // position is between -1.0F & 0.0F OR 0.0F & 1.0F
            view.setAlpha(1.5F - Math.abs(position));
        }
    }
}