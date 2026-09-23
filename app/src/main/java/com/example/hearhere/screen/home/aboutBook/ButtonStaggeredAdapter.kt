package com.example.hearhere.screen.home.aboutBook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.hearhere.R
import com.example.hearhere.models.Category


class ButtonStaggeredAdapter(
    private val buttonList: List<Category>
) : RecyclerView.Adapter<ButtonStaggeredAdapter.ButtonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.button_staggered_list, parent, false
        )
        return ButtonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val currentItem = buttonList[position]
        holder.button.text = currentItem.title
    }

    override fun getItemCount(): Int {
        return buttonList.size
    }

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val button: Button = itemView.findViewById(R.id.idButton)
    }
}