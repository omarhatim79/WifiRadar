package com.example.wifiradar

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class SecurityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)

        fun navTo(cls: Class<*>) {
            startActivity(Intent(this, cls).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
            @Suppress("DEPRECATION")
            overridePendingTransition(0, 0)
        }

        findViewById<View>(R.id.btn_back).setOnClickListener {
            finish()
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
        }
        findViewById<View>(R.id.btn_scan).setOnClickListener { /* TODO */ }

        findViewById<View>(R.id.nav_home).setOnClickListener { navTo(MainActivity::class.java) }
        findViewById<View>(R.id.nav_capture).setOnClickListener { navTo(CaptureActivity::class.java) }
        findViewById<View>(R.id.nav_hidden).setOnClickListener { navTo(HiddenNetworksActivity::class.java) }
        findViewById<View>(R.id.nav_channel_rating).setOnClickListener { navTo(ChannelRatingActivity::class.java) }
    }
}
