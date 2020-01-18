package com.example.allie

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomCanvasView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

//    private val person = Person(100, 300, context)
//    private val p2 = Person(200, 400, context)
    private val eyes = Eyes(100, 300, context);

    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // DRAW STUFF HERE
//        person.draw(canvas)
        eyes.draw(canvas)
        invalidate()
    }

}