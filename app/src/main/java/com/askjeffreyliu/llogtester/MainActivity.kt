package com.askjeffreyliu.llogtester

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing an empty ArrayList to be filled with list
        val list: ArrayList<String> = ArrayList()

        // Loads items into the ArrayList
        addItems(list)

        // Creates a vertical Layout Manager
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it
        myRecyclerView.adapter = AnimalAdapter(list, this)

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        myRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    // Adds list to the empty list ArrayList
    private fun addItems(list: ArrayList<String>) {
        list.add("dog")
        list.add("cat")
        list.add("owl")
        list.add("cheetah")
        list.add("raccoon")
        list.add("bird")
        list.add("snake")
        list.add("lizard")
        list.add("hamster")
        list.add("bear")
        list.add("lion")
        list.add("tiger")
        list.add("horse")
        list.add("frog")
        list.add("fish")
        list.add("shark")
        list.add("turtle")
        list.add("elephant")
        list.add("cow")
        list.add("beaver")
        list.add("bison")
        list.add("porcupine")
        list.add("rat")
        list.add("mouse")
        list.add("goose")
        list.add("deer")
        list.add("fox")
        list.add("moose")
        list.add("buffalo")
        list.add("monkey")
        list.add("penguin")
        list.add("parrot")
    }
}
