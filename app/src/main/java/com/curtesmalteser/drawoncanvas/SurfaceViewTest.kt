package com.curtesmalteser.drawoncanvas

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View

/**
 * Created by António "Curtes Malteser" Bastião on 18/04/2019.
 */
class SurfaceViewTest @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        Log.d("SVT", "surfaceChanged")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.d("SVT", "surfaceDestroyed")
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.d("SVT", "surfaceCreated")
        val canvas = holder?.lockCanvas()
        canvas?.drawColor(Color.RED)

        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f
        paint.color = Color.GREEN

        canvas?.drawCircle(width/2.toFloat(), height/2.toFloat(), height/2.toFloat(), paint)
        holder?.unlockCanvasAndPost(canvas)
    }

}
