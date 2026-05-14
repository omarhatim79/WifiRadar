package com.example.wifiradar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import android.widget.TextView

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnAction: TextView
    private val dots = arrayOfNulls<View>(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        if (prefs.getBoolean("onboarding_done", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewpager)
        btnAction = findViewById(R.id.btn_action)
        dots[0] = findViewById(R.id.dot_0)
        dots[1] = findViewById(R.id.dot_1)
        dots[2] = findViewById(R.id.dot_2)
        dots[3] = findViewById(R.id.dot_3)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bottom_section)) { view, insets ->
            val navBar = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            view.setPadding(0, 0, 0, navBar.bottom)
            insets
        }

        viewPager.adapter = OnboardingAdapter(this)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDots(position)
                updateButton(position)
            }
        })

        updateDots(0)
        updateButton(0)

        btnAction.setOnClickListener {
            val current = viewPager.currentItem
            if (current < 3) {
                viewPager.currentItem = current + 1
            } else {
                getSharedPreferences("app_prefs", MODE_PRIVATE)
                    .edit().putBoolean("onboarding_done", true).apply()
                startActivity(Intent(this, MainActivity::class.java))
                @Suppress("DEPRECATION")
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
                finish()
            }
        }
    }

    private fun updateDots(active: Int) {
        val activePx = (8 * resources.displayMetrics.density + 0.5f).toInt()
        val inactivePx = (6 * resources.displayMetrics.density + 0.5f).toInt()
        dots.forEachIndexed { i, dot ->
            dot ?: return@forEachIndexed
            val lp = dot.layoutParams
            if (i == active) {
                lp.width = activePx; lp.height = activePx; dot.alpha = 1f
            } else {
                lp.width = inactivePx; lp.height = inactivePx; dot.alpha = 0.35f
            }
            dot.layoutParams = lp
        }
    }

    private fun updateButton(position: Int) {
        if (position == 3) {
            btnAction.setBackgroundResource(R.drawable.bg_ob_button_start)
            btnAction.setTextColor(ContextCompat.getColor(this, R.color.black))
            btnAction.text = getString(R.string.ob_get_started)
        } else {
            btnAction.setBackgroundResource(R.drawable.bg_ob_button)
            btnAction.setTextColor(ContextCompat.getColor(this, R.color.black))
            btnAction.text = getString(R.string.ob_continue)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (viewPager.currentItem > 0) {
            viewPager.currentItem -= 1
        } else {
            @Suppress("DEPRECATION")
            super.onBackPressed()
        }
    }
}
