package com.gamered

import android.app.*
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.*
import android.widget.*

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
        if (Build.VERSION.SDK_INT >= 26)
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(NotificationChannel(ch, "Game Red", NotificationManager.IMPORTANCE_LOW))
        return Notification.Builder(this, ch)
            .setContentTitle("Game Red").setContentText("aktif")
            .setSmallIcon(android.R.drawable.ic_menu_manage).build()
    }

    private fun build() {
        val lp = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply { gravity = Gravity.TOP or Gravity.START; x = 30; y = 100 }

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(0xCC000000.toInt())
            setPadding(24, 24, 24, 24)
        }

        val tv = TextView(this).apply {
            text = "GAME RED OVERLAY"
            setTextColor(0xFFFF5252.toInt())
            textSize = 16f
        }
        root.addView(tv)

        root.addView(Button(this).apply {
            text = "X"
            setOnClickListener { stopSelf() }
        })

        v = root
        wm.addView(root, lp)
    }

    override fun onDestroy() {
        super.onDestroy()
        v?.let { wm.removeView(it) }
    }
}
