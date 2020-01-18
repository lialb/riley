package com.example.allie

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        style = Paint.Style.STROKE
    }


    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // DRAW STUFF HERE

        canvas!!.drawCircle(100f, 100f, 100f, textPaint)
    }
}