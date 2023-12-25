package ru.mggtk.booknook.Adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.mggtk.booknook.R
import ru.mggtk.booknook.dataclass.Book

class BooksAdapter(private val context: Context, private var books: List<Book>, private val onAddToCartClickListener: (Book) -> Unit) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.titleTextView.text = book.title.toString()
        holder.priceTextView.text = "${book.price} руб."

        // Используем Picasso для загрузки изображения
        Picasso.get().load(book.imageUrl).into(holder.imageView)

        holder.addToCartButton.setOnClickListener {
            animateAddToCartButton(holder.addToCartButton)
            onAddToCartClickListener.invoke(book)
        }

        // Устанавливаем отступ снизу для всех элементов, кроме последнего
        val marginBottom = if (position == books.size - 1) 0 else dpToPx(8)
        val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
        params.bottomMargin = marginBottom
        holder.itemView.layoutParams = params

    }

    private fun dpToPx(dp: Int): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (dp * density).toInt()
    }
    fun updateItems(newItems: List<Book>) {
        books = newItems
        notifyDataSetChanged()
    }

    private fun animateAddToCartButton(button: Button) {
        val container = button.parent as? ViewGroup ?: return

        val whiteButton = Button(context)
        whiteButton.setBackgroundResource(R.drawable.rounded_white_button)
        whiteButton.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

        // Добавляем белую кнопку в начало контейнера (на передний план)
        container.addView(whiteButton, 0)

        val startAlpha = 0f
        val endAlpha = 1f

        val alphaAnimation = ValueAnimator.ofFloat(startAlpha, endAlpha)
        alphaAnimation.duration = 500

        alphaAnimation.addUpdateListener { animator ->
            whiteButton.alpha = 1 - animator.animatedValue as Float
        }

        alphaAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // После завершения анимации удаляем белую кнопку
                container.removeView(whiteButton)
            }
        })

        alphaAnimation.start()
    }

    override fun getItemCount(): Int {
        return books.size
    }
}
