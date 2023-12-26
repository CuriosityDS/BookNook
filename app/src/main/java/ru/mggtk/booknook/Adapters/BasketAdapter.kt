package ru.mggtk.booknook.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mggtk.booknook.R
import ru.mggtk.booknook.dataclass.Book

class BasketAdapter(
    private val context: Context,
    private var items: List<Book>,
    private var onDeleteItemClickListener: ((Book) -> Unit)? = null,
    private val onIncreaseQuantityClick: ((Book) -> Unit)? = null,
    private val onDecreaseQuantityClick: ((Book) -> Unit)? = null
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    fun updateItems(newItems: List<Book>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun setOnDeleteClickListener(listener: (Book) -> Unit) {
        onDeleteItemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantityTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        val increaseQuantityButton: Button = itemView.findViewById(R.id.increaseQuantityButton)
        val decreaseQuantityButton: Button = itemView.findViewById(R.id.decreaseQuantityButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = items[position]

        holder.titleTextView.text = book.title
        holder.priceTextView.text = "${book.roundedPrice} руб." // Используем округленную цену
        holder.quantityTextView.text = "${context.getString(R.string.quantity)}: ${book.quantity}"

        holder.deleteButton.setOnClickListener {
            onDeleteItemClickListener?.invoke(book)
        }

        holder.increaseQuantityButton.setOnClickListener {
            onIncreaseQuantityClick?.invoke(book)
        }

        holder.decreaseQuantityButton.setOnClickListener {
            onDecreaseQuantityClick?.invoke(book)
        }
    }

    fun notifyDataChanged() {
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


