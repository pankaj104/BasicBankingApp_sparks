package com.example.banking

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_customer_list.*

class HistoryActivity : AppCompatActivity() {
    private lateinit var helper: MyDBHelper
    private lateinit var db : SQLiteDatabase
    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter:HistoryAdapter
    private lateinit var mapp:HashMap<Int,Customer>

    private lateinit var adapter:MyAdapter

    private lateinit var rs: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
recyclerView.layoutManager=LinearLayoutManager(this)
     //   val adapter: HistoryAdapter= HistoryAdapter(this,CustomerList)

    }
    private fun fetchData(){

        }
      //  adapter.updateList(list)
    }

//        helper = MyDBHelper(applicationContext)
//        db = helper .readableDatabase
//recyclerView=findViewById(R.id.recyclerView)
//
//        layoutManager = LinearLayoutManager(this)
//adapter = HistoryAdapter(this)

//        recyclerView.layoutManager=LinearLayoutManager(this)
//      adapter1= HistoryAdapter(this)
//recyclerView.adapter=adapter
//fetchData()
//recyclerAdapter=recyclerAdapter(Model)