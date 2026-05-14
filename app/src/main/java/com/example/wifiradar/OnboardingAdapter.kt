package com.example.wifiradar

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private data class Page(val asset: String, val headline: String, val subtext: String)

    private val pages = listOf(
        Page(
            "img/Frame 40.png",
            "Monitor Every Packet\nin Real-Time",
            "Capture and analyze TCP, UDP, ICMP traffic\nwith deep protocol inspection"
        ),
        Page(
            "img/Frame 41.png",
            "Detect Hidden Threats\nInstantly",
            "Identify rogue access points, hidden SSIDs,\nand MITM attack vectors before they reach you"
        ),
        Page(
            "img/Frame 41-1.png",
            "Optimize Your Network\nPerformance",
            "Get AI-powered channel recommendations,\nlatency scores, and actionable fixes"
        )
    )

    override fun getItemCount() = 4

    override fun getItemViewType(position: Int) = if (position == 3) 1 else 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            PageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_onboarding_page, parent, false))
        } else {
            Page4ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_onboarding_page4, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PageViewHolder) {
            val page = pages[position]
            holder.tvHeadline.text = page.headline
            holder.tvSubtext.text = page.subtext
            try {
                val bm = BitmapFactory.decodeStream(context.assets.open(page.asset))
                holder.ivIllustration.setImageBitmap(bm)
            } catch (_: Exception) {}
        }
    }

    inner class PageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIllustration: ImageView = view.findViewById(R.id.iv_illustration)
        val tvHeadline: TextView = view.findViewById(R.id.tv_headline)
        val tvSubtext: TextView = view.findViewById(R.id.tv_subtext)
    }

    inner class Page4ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
