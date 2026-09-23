package com.example.hearhere.screen.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearhere.R
import com.example.hearhere.models.Book

class BookAdapter(
    val context: Context,
    private val books: List<Book>,
    private val onBookItemClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.ivImage)
        private val title = itemView.findViewById<TextView>(R.id.tvTitle)
        private val author = itemView.findViewById<TextView>(R.id.tvName)

        fun bind(book: Book) {
            title.text = book.title
            Glide.with(context)
                .load(book.thumbnail_link)
                .into(image)
            //image.text = book.imageUrl // Assuming image URL is being handled somewhere else, like Glide
            author.text = book.author_name
            itemView.setOnClickListener {
                onBookItemClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_list_item_horizontal, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }
}
/*class BookAdapter(
    private val books: List<BookModel>,
    private val itemClick: ContainerBookAdapter.ItemClick
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.ivImage)
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val auther = itemView.findViewById<TextView>(R.id.tvName)

        fun bind(book: BookModel) {
            title.text = book.bookTitle
            //image.text = item.imageUrl
            auther.text = book.bookAuther
            itemView.setOnClickListener {
                itemClick.onBookItemClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_list_item_horizontal,
            parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }
}*/