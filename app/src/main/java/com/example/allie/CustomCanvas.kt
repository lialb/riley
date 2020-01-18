package com.example.allie

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.allie.AnimationStepImplementations.AnimationStep

class CustomCanvasView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val person = Person(100, 300, context)
    private val background = Background(context)

    private val canvasDrawables: MutableList<CanvasDrawable> = ArrayList()

    fun addDrawable(canvasDrawable: CanvasDrawable) {
        canvasDrawables.clear()
        canvasDrawables.add(canvasDrawable)
    }

    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // DRAW STUFF HERE
        background.draw(canvas)
        for (item in canvasDrawables) item.draw(canvas)

        invalidate()
    }

    fun getBackgroundDrawable(): Background {
        return background
    }

    fun getPerson(): Person {
        return person
    }

}