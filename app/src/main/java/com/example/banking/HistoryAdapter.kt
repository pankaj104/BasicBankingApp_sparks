package com.example.banking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.transaction_info.*
import kotlinx.android.synthetic.main.transfer_history_list.*

//class HistoryAdapter {
//}
//


class HistoryAdapter(val context: Context, val HistoryList: List<Model>): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name1: TextView = view.findViewById(R.id.name1)
        val name2: TextView = view.findViewById(R.id.name2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transfer_history_list, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val book = HistoryList[position]
        holder.name1.text= book.getName1()
        holder.name2.text= book.getName2()
//        holder.txtBookRating.text=book.bookRating
//        holder.txtBookPrice.text=book.bookPrice

    }

    override fun getItemCount(): Int {
        return HistoryList.size
    }
}