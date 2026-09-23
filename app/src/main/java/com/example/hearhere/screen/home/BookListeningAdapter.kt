package com.example.hearhere.screen.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hearhere.R
import com.example.hearhere.models.LastBookLestining

class BookListeningAdapter(private val context: Context, private val items: List<LastBookLestining>) :
    RecyclerView.Adapter<BookListeningAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.item_title)
        val progress : ProgressBar= view.findViewById(R.id.item_progress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lestining_book_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageView=holder.itemView.findViewById<ImageView>(R.id.item_image)
        val item = items[position]
        imageView.setImageResource(R.drawable.logo) // placeholder
        holder.titleView.text = item.title
        holder.progress.progress = item.progress
    }

    override fun getItemCount(): Int = items.size
}
