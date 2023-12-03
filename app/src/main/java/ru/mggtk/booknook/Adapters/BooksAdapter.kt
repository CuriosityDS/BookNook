package ru.mggtk.booknook.Adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.mggtk.booknook.R
import ru.mggtk.booknook.dataclass.Book

class BooksAdapter(private val books: List<Book>, private val onAddToCartClickListener: (Book) -> Unit) :
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
        holder.titleTextView.text = book.title
        holder.priceTextView.text = "${book.price} руб."

        // Используем Picasso для загрузки изображения
        Picasso.get().load(book.imageUrl).into(holder.imageView)

        holder.addToCartButton.setOnClickListener {
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


    override fun getItemCount(): Int {
        return books.size
    }
}
