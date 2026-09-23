package com.example.hearhere.screen.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hearhere.R
import com.example.hearhere.models.Book
import com.example.hearhere.models.Collection

class ContainerBookAdapter(
    val context: Context,
    private val containerOfBooks: List<Collection>,
    private val onSeeAllClick: (Collection) -> Unit,
    private val onBookItemClick: (Book) -> Unit
) : RecyclerView.Adapter<ContainerBookAdapter.ContainerBookViewHolder>() {

    inner class ContainerBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.txtVerticalName)
        private val seeAll = itemView.findViewById<TextView>(R.id.txtSeeAll)
        private val books = itemView.findViewById<RecyclerView>(R.id.itemVerticalRecycler)

        fun bind(item: Collection) {
            title.text = item.title
            books.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            books.adapter = BookAdapter(context,item.books, onBookItemClick)

            seeAll.setOnClickListener {
                onSeeAllClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainerBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_list_item_vertical, parent, false)
        return ContainerBookViewHolder(view)
    }

    override fun getItemCount(): Int = containerOfBooks.size

    override fun onBindViewHolder(holder: ContainerBookViewHolder, position: Int) {
        holder.bind(containerOfBooks[position])
    }
}
/*
* class ContainerBookAdapter(
    val containerOfBooks: List<ContainerOfBooks>,
    private val itemClick: ItemClick
) : RecyclerView.Adapter<ContainerBookAdapter.ContainerBookViewHolder>() {

    inner class ContainerBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.txtVerticalName)
        val seeAll = itemView.findViewById<TextView>(R.id.txtSeeAll)
        val books = itemView.findViewById<RecyclerView>(R.id.itemVerticalRecycler)
        val underline = itemView.findViewById<View>(R.id.underline)

        fun bind(item: ContainerOfBooks) {
            title.text = item.title
            books.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            books.adapter = BookAdapter(item.books, itemClick)

            seeAll.setOnClickListener {
                itemClick.onITemClickSeeAll(item)
            }


        }
    }

    interface ItemClick {
        fun onITemClickSeeAll(item: ContainerOfBooks)
        fun onBookItemClick(book: BookModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainerBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_list_item_vertical, parent, false)
        return ContainerBookViewHolder(view)
    }

    override fun getItemCount(): Int = containerOfBooks.size

    override fun onBindViewHolder(holder: ContainerBookViewHolder, position: Int) {
        holder.bind(containerOfBooks[position])
    }
}

* */