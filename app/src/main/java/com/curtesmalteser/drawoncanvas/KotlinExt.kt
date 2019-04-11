package com.curtesmalteser.drawoncanvas

import android.content.res.Resources
import android.util.TypedValue
import android.view.View

////////////////////////////
/////// ResourcesExt ///////
////////////////////////////

fun Resources.fontSPtoPixels(dimen: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dimen.toFloat(),
            displayMetrics).toInt()
}

///////////////////////
/////// ViewExt ///////
///////////////////////
fun View.findCenter(): Center {
    return Center((width / 2).toFloat(), (height / 2).toFloat())
}

data class Center(val x: Float, val y: Float)