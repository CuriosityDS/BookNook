package ru.mggtk.booknook.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mggtk.booknook.R
import ru.mggtk.booknook.dataclass.Book

class BasketAdapter(
    private var items: List<Book>,
    private var onDeleteClickListener: (Book) -> Unit
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {


    fun updateItems(newItems: List<Book>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun setOnDeleteClickListener(listener: (Book) -> Unit) {
        onDeleteClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = items[position]
        holder.titleTextView.text = book.title
        holder.priceTextView.text = "${book.price} руб."

        // Обработчик нажатия кнопки "удалить товар"
        holder.deleteButton.setOnClickListener {
            onDeleteClickListener.invoke(book)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


