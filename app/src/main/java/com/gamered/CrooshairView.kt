package com.gamered

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CrosshairView(context: Context) : View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 4f
        style = Paint.Style.STROKE
    }

    private val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val cx = width / 2f
        val cy = height / 2f
        val gap = 12f
        val len = 24f
        canvas.drawLine(cx, cy - gap - len, cx, cy - gap, paint)
        canvas.drawLine(cx, cy + gap, cx, cy + gap + len, paint)
        canvas.drawLine(cx - gap - len, cy, cx - gap, cy, paint)
        canvas.drawLine(cx + gap, cy, cx + gap + len, cy, paint)
        canvas.drawCircle(cx, cy, 2f, dotPaint)
    }
}
