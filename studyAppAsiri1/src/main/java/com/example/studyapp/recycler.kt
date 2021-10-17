package com.example.studyapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class recycler (private val activity:Activity,private val sContant: ArrayList<ArrayList<String>>): RecyclerView.Adapter<recycler.ItemHolder>() {
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.itemView.apply {
            textView.text = sContant[position][0]
            textView2.text= sContant[position][1]
            cv.setOnClickListener { Alert(activity,sContant[position][0],"ather Delale") }


        }
    }

    override fun getItemCount()= sContant.size

}