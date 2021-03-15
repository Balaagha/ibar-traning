package com.example.myapplication.androidjetpackcleanarchitecturetestting.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.recyclerview.model.Fruit
import kotlinx.android.synthetic.main.list_item_recycler_view_intro.view.*

class RecyclerViewIntroAdapter(private val list: List<Fruit>, private val clickListener: (Fruit)->Unit): RecyclerView.Adapter<RecyclerViewIntroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewIntroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_recycler_view_intro,parent,false)
        return RecyclerViewIntroViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewIntroViewHolder, position: Int) {
        holder.bind(list.get(position),clickListener)
    }

}

class RecyclerViewIntroViewHolder(val view: View): RecyclerView.ViewHolder(view){
    fun bind(fruit: Fruit, clickListener: (Fruit)->Unit){
        view.tvName.text  = fruit.name
        view.setOnClickListener{
            clickListener(fruit)
        }
    }
}