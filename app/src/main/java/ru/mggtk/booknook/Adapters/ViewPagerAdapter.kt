package ru.mggtk.booknook.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mggtk.booknook.R

class ViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {

    private val colors = intArrayOf(
        R.color.bg,
        R.color.bg,
        R.color.bg,
        R.color.bg
    )

    private val image = intArrayOf(
        R.drawable.banner,
        R.drawable.banner,
        R.drawable.banner,
        R.drawable.banner
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))

    override fun getItemCount(): Int = 30


    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        val container:RelativeLayout = findViewById(R.id.container)
        val imageView:ImageView = findViewById(R.id.imageView15)
        container.setBackgroundResource(R.color.bg)
        imageView.setImageResource(R.drawable.banner)
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)