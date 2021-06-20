package com.example.banking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val listener: CustomerItemClicked ) : RecyclerView.Adapter<CustomerViewHolder>() {
    val items: ArrayList<Customer> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        val viewHolder = CustomerViewHolder(view)
        view.setOnClickListener{
            listener.onCustomerClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
            return items.size
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val current = items[position]
        holder.name.text = current.getFullName()
        holder.balance.text = current.getBalance().toString()
        holder.email.text= current.getEmail().toString()

    }

    fun updateList(newitems: ArrayList<Customer>) {
        items.clear()
        items.addAll(newitems)

        notifyDataSetChanged()
    }

}


class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val balance: TextView = itemView.findViewById(R.id.balance)
    val email: TextView=itemView.findViewById(R.id.email)

}

interface CustomerItemClicked {
    fun onCustomerClicked(item: Customer)
}