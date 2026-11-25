package com.gamered

import android.app.*
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class OverlayService : Service() {
    private lateinit var wm: WindowManager
    private var v: View? = null

    override fun onBind(i: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startForeground(1, notif())
        wm = getSystemService(WINDOW_SERVICE) as WindowManager
        build()
    }

    private fun notif(): Notification {
        val ch = "gamered"
        if (Build.VERSION.SDK_INT >= 26) {
            val nm = getSystemService(NotificationManager::class.java)
            val channel = NotificationChannel(ch, "Game Red", NotificationManager.IMPORTANCE_LOW)
            nm.createNotificationChannel(channel)
        }
        return Notification.Builder(this, ch)
            .setContentTitle("Game Red")
            .setContentText("aktif")
            .setSmallIcon(android.R.drawable.ic_menu_manage)
            .build()
    }

    private fun build() {
        val lp = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        lp.gravity = Gravity.TOP or Gravity.START
        lp.x = 30
        lp.y = 100

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setBackgroundColor(Color.parseColor("#CC000000"))
        root.setPadding(24, 24, 24, 24)

        val tv = TextView(this)
        tv.text = "GAME RED OVERLAY"
        tv.setTextColor(Color.parseColor("#FF5252"))
        tv.textSize = 16f
        root.addView(tv)

        val btn = Button(this)
        btn.text = "X"
        btn.setOnClickListener { stopSelf() }
        root.addView(btn)

        v = root
        wm.addView(root, lp)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (v != null) wm.removeView(v)
    }
}
