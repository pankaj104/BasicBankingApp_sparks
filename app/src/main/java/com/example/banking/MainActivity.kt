package com.example.banking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loadCustomers(view: View) {
        val intent  = Intent(this,CustomerList::class.java)
        startActivity(intent)
    }

   // private lateinit var linearLayoutManager: LinearLayoutManager
// for right menu connect

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.history_menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//    // for item clicked and change Activity
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId)
//        {
//            R.id.menu_history ->
//            {
//                startActivity(Intent(this,HistoryActivity:: class.java))
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

//    fun openNewTask(view: View) {
//        startActivity(Intent(this,TaskActivity:: class.java))
//    }
}
