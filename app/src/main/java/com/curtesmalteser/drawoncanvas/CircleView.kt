package com.curtesmalteser.drawoncanvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by António "Curtes Malteser" Bastião on 11/04/2019.
 */
class CircleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val oval = RectF()
    val mPaintRed = Paint()
    val mPaintGreen = Paint()
    val mPaintBlue = Paint()
    val mPaintBlack = Paint()
    val mPaintGrad = Paint()

    val red = Color.argb(255, 255, 0, 0)
    val green = Color.argb(255, 0, 255, 0)
    val blue = Color.argb(255, 0, 0, 255)
    val black = Color.argb(255, 0, 0, 0)


    val mStyle = Paint.Style.STROKE
    val mStrokeWidth = resources.convertDpIntoPixel(32f)

    val center by lazy {
        findCenter()
    }

    init {
        mPaintRed.myPaint(red, mStyle, mStrokeWidth)
        mPaintGreen.myPaint(green, mStyle, mStrokeWidth)
        mPaintBlue.myPaint(blue, mStyle, mStrokeWidth)
        mPaintBlack.myPaint(black, mStyle, mStrokeWidth)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val radius = (Math.min(center.x, center.y) * 0.9f) - mStrokeWidth / 2

        // Rotate canvas 0º will be the top
        canvas.rotate(-90f, center.x, center.y)

        val left = center.x - radius
        val top = center.y - radius
        val right = center.x + radius
        val bottom = center.y + radius
        oval.set( left,  top, right , bottom)

        canvas.drawCircle(center.x, center.y, radius, mPaintBlack)
        
        mPaintGrad.makeRadGrad(
                strokeWidth = mStrokeWidth,
                cx = center.x,
                cy = center.y,
                listColors = listOf(blue, red, green),
                positionsList = listOf((0.05f), (0.15f), (0.2f)))


        canvas.drawArc(oval, 0F, 135F, false, mPaintGrad)
        //canvas.drawArc(oval, 200F, 30F, false, mPaintGreen)
        //canvas.drawArc(oval, 230F, 80F, false, mPaintBlue)
        //canvas.drawArc(oval, 310F, 50F, false, mPaintBlack)
    }
}