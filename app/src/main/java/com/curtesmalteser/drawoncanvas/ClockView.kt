package com.curtesmalteser.drawoncanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.util.*

/**
 * Created by António "Curtes Malteser" Bastião on 03/03/2018.
 * based on https://www.youtube.com/watch?v=ybKgq6qqTeA
 */

class ClockView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mHeight = 0
    private var mWidth = 0
    private var padding = 0
    private var fontSize = 0
    private val numeralSpacing = 0
    private var handTruncation: Int = 0
    private var hourHandTruncation = 0
    private var radius: Int = 0
    private val paint = Paint()
    private var isInit: Boolean = false
    private val rect = Rect()


    private fun initClock() {
        mHeight = height
        mWidth = width
        padding = numeralSpacing + 50
        fontSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13f,
                resources.displayMetrics).toInt()

        val min = Math.min(height, width)
        radius = min / 2 - padding
        handTruncation = min / 20
        hourHandTruncation = min / 7
        isInit = true
    }

    override fun onDraw(canvas: Canvas) {
        if (!isInit)
            initClock()

        canvas.drawColor(Color.BLACK)
        drawCircle(canvas)
        drawCenter(canvas)
        drawNumeral(canvas)
        drawHands(canvas)

        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawHand(canvas: Canvas, loc: Double, isHour: Boolean) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius = if (isHour) radius - handTruncation - hourHandTruncation else radius - handTruncation
        canvas.drawLine((width / 2).toFloat(),
                (height / 2).toFloat(),
                (width / 2 + Math.cos(angle) * handRadius).toFloat(),
                (height / 2 + Math.sin(angle) * handRadius).toFloat(),
                paint)
    }

    private fun drawHands(canvas: Canvas) {
        val c = Calendar.getInstance()
        var hour = c.get(Calendar.HOUR_OF_DAY).toFloat()
        hour = if (hour > 12) hour - 12 else hour
        drawHand(canvas, ((hour + c.get(Calendar.MINUTE) / 60) * 5f).toDouble(), true)
        drawHand(canvas, c.get(Calendar.MINUTE).toDouble(), false)
        drawHand(canvas, c.get(Calendar.SECOND).toDouble(), false)
    }

    private fun drawNumeral(canvas: Canvas) {
        paint.textSize = fontSize.toFloat()
        for (i in 1..12) {
            val tmp = i.toString()
            paint.getTextBounds(tmp, 0, tmp.length, rect)
            val angle = Math.PI / 6 * (i - 3)
            val x = (width / 2 + Math.cos(angle) * radius - rect.width() / 2).toInt()
            val y = ((height / 2).toDouble() + Math.sin(angle) * radius + (rect.height() / 2).toDouble()).toInt()
            canvas.drawText(tmp, x.toFloat(), y.toFloat(), paint)
        }
    }

    private fun drawCenter(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 12f, paint)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.reset()
        paint.color = ContextCompat.getColor(context, android.R.color.white)
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), (radius + padding - 10).toFloat(), paint)
    }
}
