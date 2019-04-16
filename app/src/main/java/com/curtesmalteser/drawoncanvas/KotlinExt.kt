package com.curtesmalteser.drawoncanvas

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.SweepGradient
import android.util.TypedValue
import android.view.View

////////////////////////////
/////// ResourcesExt ///////
////////////////////////////

fun Resources.fontSPtoPixels(dimen: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dimen.toFloat(),
            displayMetrics).toInt()
}

fun Resources.convertDpIntoPixel(dp: Float): Float {
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            displayMetrics
    )
}

///////////////////////
/////// ViewExt ///////
///////////////////////
fun View.findCenter() = Center((width / 2).toFloat(), (height / 2).toFloat())

data class Center(val x: Float, val y: Float)

fun Canvas.drawRoundedEdge( angleDeg: Double, radius: Float, strokeWidth: Float, paint: Paint) {
    val coordX = (width / 2) + radius * Math.cos(Math.toRadians(angleDeg))
    val coordY = (height / 2) + radius * Math.sin(Math.toRadians(angleDeg))

    paint.myPaint(paint.color, Paint.Style.FILL, 1f)
    drawCircle(coordX.toFloat() , coordY.toFloat(), (strokeWidth/2), paint)
}

////////////////////////
/////// PaintExt ///////
////////////////////////

fun Paint.myPaint(color: Int, style: Paint.Style, strokeWidth: Float) {
    this.isAntiAlias = true
    this.color = color
    this.style = style
    this.strokeWidth = strokeWidth
}

fun Paint.makeRadGrad(strokeWidth: Float, cx: Float, cy: Float, listColors: List<Int>, positionsList: List<Float>?) {
    val gradient = SweepGradient(cx, cy, listColors.toIntArray(), positionsList?.toFloatArray())

    this.strokeWidth = strokeWidth
    this.style = Paint.Style.STROKE
    this.isDither = true
    this.shader = gradient
}

////////////////////////
/////// ColorExt ///////
////////////////////////

