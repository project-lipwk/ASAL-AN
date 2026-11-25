package com.gamered

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat

class CrosshairView(context: Context) : View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.red_glow)
        strokeWidth = 4f
        style = Paint.Style.STROKE
    }

    private val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.red_glow)
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val cx = width / 2f
        val cy = height / 2f
        val gap = 12f
        val len = 24f

        // garis atas
        canvas.drawLine(cx, cy - gap - len, cx, cy - gap, paint)
        // garis bawah
        canvas.drawLine(cx, cy + gap, cx, cy + gap + len, paint)
        // garis kiri
        canvas.drawLine(cx - gap - len, cy, cx - gap, cy, paint)
        // garis kanan
        canvas.drawLine(cx + gap, cy, cx + gap + len, cy, paint)
        // titik tengah
        canvas.drawCircle(cx, cy, 2f, dotPaint)
    }
}
