package com.example.hearhere.screen.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearhere.R
import com.example.hearhere.models.dbModels.DBBookModel

class BookLibraryAdapter (
    val context: Context,
    private val books: List<DBBookModel>,
    private val onBookItemClick: (DBBookModel) -> Unit
) : RecyclerView.Adapter<BookLibraryAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.ivBookImage)
        private val title = itemView.findViewById<TextView>(R.id.tvBookTitle)
        private val author = itemView.findViewById<TextView>(R.id.tvBookName)
        private val description = itemView.findViewById<TextView>(R.id.txBookDescription)

        fun bind(book: DBBookModel) {
            title.text = book.title
            Glide.with(context)
                .load(book.thumbnail_link)
                .into(image)
            //image.text = book.imageUrl // Assuming image URL is being handled somewhere else, like Glide
            author.text = book.author_name
            description.text = book.description
            itemView.setOnClickListener {
                onBookItemClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.library_book, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }
}