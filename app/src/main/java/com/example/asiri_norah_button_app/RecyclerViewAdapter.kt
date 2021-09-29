package com.example.asiri_norah_button_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.asiri_norah_button_app.RecyclerViewAdapter.*

class RecyclerViewAdapter (private val context: Context, private val myList: List<String>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
val myListItem= LayoutInflater.from(context).inflate(R.layout.myitem_list,viewGroup,false)
        return ViewHolder(myListItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val color =myList[position]

        holder.itemView.apply {
            tv_item = color
        }
    }

    override fun getItemCount() : Int {return myList.size}

    inner class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(myInt: String){
            itemView.tv_item
        }
    }
}