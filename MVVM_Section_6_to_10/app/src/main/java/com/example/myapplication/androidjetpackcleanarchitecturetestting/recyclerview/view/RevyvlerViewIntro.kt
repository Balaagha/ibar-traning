package com.example.myapplication.androidjetpackcleanarchitecturetestting.recyclerview.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.androidjetpackcleanarchitecturetestting.recyclerview.adapter.RecyclerViewIntroAdapter
import com.example.myapplication.androidjetpackcleanarchitecturetestting.recyclerview.model.Fruit
import kotlinx.android.synthetic.main.activity_revyvler_view_intro.*

class RevyvlerViewIntro : AppCompatActivity() {
    val fruitList = arrayListOf<Fruit>(Fruit("Mango","Abc"),Fruit("Apple","Imondi"),
        Fruit("Banana","Carogo")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revyvler_view_intro)

        recyclerView.setBackgroundColor(Color.YELLOW)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewIntroAdapter(fruitList,{
            selectedFruitItem: Fruit -> listItemClicked(selectedFruitItem)
        })
    }

    private fun listItemClicked(fruit: Fruit){
        Toast.makeText(this@RevyvlerViewIntro,"Supplier name is ${fruit.supplier}",Toast.LENGTH_LONG).show()
    }

}



