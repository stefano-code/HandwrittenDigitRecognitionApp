package com.android.test.handwrittendigitrecognition

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var path = Path()
    private var paint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 4f
        isAntiAlias = true
    }

    private var bitmap: Bitmap? = null
    private var canvasBitmap: Canvas? = null

    private val internalSize = 64

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        bitmap = Bitmap.createBitmap(internalSize, internalSize, Bitmap.Config.ARGB_8888)
        canvasBitmap = Canvas(bitmap!!)
        canvasBitmap?.drawColor(Color.BLACK)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        bitmap?.let {
            val destRect = Rect(0, 0, width, height)
            canvas.drawBitmap(it, null, destRect, null)
        }

        // 👉 Disegna il path corrente (preview in tempo reale)
        val scaleX = width.toFloat() / internalSize
        val scaleY = height.toFloat() / internalSize

        canvas.save()
        canvas.scale(scaleX, scaleY)
        canvas.drawPath(path, paint)
        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val scaleX = internalSize.toFloat() / width
        val scaleY = internalSize.toFloat() / height

        val x = event.x * scaleX
        val y = event.y * scaleY

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }

            MotionEvent.ACTION_UP -> {
                canvasBitmap?.drawPath(path, paint)
                path.reset()
            }
        }

        invalidate()
        return true
    }

    fun clear() {
        canvasBitmap?.drawColor(Color.BLACK)
        invalidate()
    }

    fun getBitmap(): Bitmap? = bitmap
}