package com.example.allie

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomCanvasView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private val person = Person(100, 300, context)
    private val background = Background(context)
    private val money = StackDrawable(10, 60, R.drawable.money_stack, context);

    // Called when the view should render its content.
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // DRAW STUFF HERE
        background.draw(canvas)
//        person.draw(canvas)
        money.draw(canvas)
        invalidate()
    }

    fun getBackgroundDrawable(): Background {
        return background
    }

    fun getPerson(): Person {
        return person
    }

    fun getMoney(): StackDrawable {
        return money
    }

}