package com.example.wifiradar

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun forward(cls: Class<*>) {
            startActivity(
                Intent(this, cls),
                ActivityOptions.makeCustomAnimation(this, R.anim.enter_from_right, R.anim.exit_to_left).toBundle()
            )
        }

        fun navTo(cls: Class<*>) {
            startActivity(Intent(this, cls).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
            @Suppress("DEPRECATION")
            overridePendingTransition(0, 0)
        }

        findViewById<MaterialCardView>(R.id.card_packet_capture).setOnClickListener { forward(CaptureActivity::class.java) }
        findViewById<MaterialCardView>(R.id.card_gaming_voip).setOnClickListener { forward(CaptureActivity::class.java) }
        findViewById<MaterialCardView>(R.id.card_hidden_networks).setOnClickListener { forward(HiddenNetworksActivity::class.java) }
        findViewById<MaterialCardView>(R.id.card_rogue_ap).setOnClickListener { forward(SecurityActivity::class.java) }
        findViewById<MaterialCardView>(R.id.card_wifi_optimization).setOnClickListener { forward(WifiOptimizationActivity::class.java) }
        findViewById<MaterialCardView>(R.id.card_channel_rating).setOnClickListener { forward(ChannelRatingActivity::class.java) }

        findViewById<View>(R.id.nav_capture).setOnClickListener { navTo(CaptureActivity::class.java) }
        findViewById<View>(R.id.nav_hidden).setOnClickListener { navTo(HiddenNetworksActivity::class.java) }
        findViewById<View>(R.id.nav_security).setOnClickListener { navTo(SecurityActivity::class.java) }
        findViewById<View>(R.id.nav_channel_rating).setOnClickListener { navTo(ChannelRatingActivity::class.java) }
    }
}
