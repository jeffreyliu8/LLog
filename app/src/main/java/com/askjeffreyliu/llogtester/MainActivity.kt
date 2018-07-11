package com.askjeffreyliu.llogtester

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager

import com.askjeffreyliu.llog.LLog
import kotlinx.android.synthetic.main.activity_main.*
import android.arch.lifecycle.ViewModelProviders

import com.askjeffreyliu.llog.MobileLog


class MainActivity : AppCompatActivity() {

    lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creates a vertical Layout Manager
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it
        adapter = RecyclerViewAdapter(null, this)
        myRecyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        myRecyclerView.addItemDecoration(dividerItemDecoration)

        fab.setOnClickListener {
            // your code to perform when the user clicks on the button
            LLog.d("test");
            LLog.e("error?")
        }

        val viewModel: ListViewModel
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        viewModel.getLogs().observe(this, Observer<List<MobileLog>> { resource ->
            adapter.setList(resource)
        })
    }
}
