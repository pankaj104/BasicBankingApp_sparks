package com.example.banking

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_customer_list.*
import kotlinx.android.synthetic.main.customer_info.view.*
import kotlinx.android.synthetic.main.transaction_info.*
import kotlinx.android.synthetic.main.transaction_info.view.*

class CustomerList : AppCompatActivity(),CustomerItemClicked {

    private lateinit var helper: MyDBHelper
    private lateinit var db : SQLiteDatabase
    private lateinit var rs: Cursor
    private lateinit var list: ArrayList<Customer>
    private lateinit var mapp:HashMap<Int,Customer>
    private var firstperson = -1
    private var secondperson = -1
    private lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)

        helper = MyDBHelper(applicationContext)
        db = helper .readableDatabase


        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        adapter = MyAdapter(this)
        recyclerView.adapter = adapter
        fetchData()






    }

    private fun fetchData(){
        rs = db.rawQuery("SELECT * FROM CUSTOMERS",null)

        list  = ArrayList<Customer>()
        mapp = HashMap<Int,Customer>()
        while(rs.moveToNext()) {

            val id = rs.getString(0).toInt()
            val item = Customer(id,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4).toInt())
            list.add(item)
            mapp.put(id,item)
        }
        adapter.updateList(list)
    }

    override fun onCustomerClicked(customer: Customer)
    {
        val inflater: LayoutInflater = LayoutInflater.from(this)
        val subView: View = inflater.inflate(R.layout.customer_info, null)
        subView.firstname.text = customer.getFullName()
        subView.email.text = customer.getEmail()
        subView.balance.text = customer.getBalance().toString()





        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Customer Information")
        builder.setView(subView)
        builder.create()

        //performing positive action
        builder.setPositiveButton("Transfer From") { dialogInterface, which ->
            //Lambda function
           // Toast.makeText(applicationContext, "clicked yes", Toast.LENGTH_SHORT).show()
            if (firstperson == -1) {
                firstperson = customer.getId()

            } else {
                firstperson = -1
            }
            updateLayout()
        }

        //performing cancel action
        builder.setNeutralButton("Cancel"){dialogInterface , which ->
            Toast.makeText(applicationContext,"clicked cancel",Toast.LENGTH_SHORT).show()
        }
        //performing negative action
        builder.setNegativeButton("Transfer To"){dialogInterface, which ->
            //Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
            if(firstperson!= -1 && secondperson == -1)
            {

                secondperson = customer.getId()
                if(firstperson == secondperson)
                {
                    Toast.makeText(applicationContext,"Invalid Operation! \n Select different customers",Toast.LENGTH_SHORT).show()
                    secondperson = -1
                }


            }
            else
            {
                secondperson = -1
            }
            updateLayout()
        }
        builder.show()
    }

    fun updateLayout()
    {
        if(firstperson != -1 || secondperson != -1)
        {
            if(firstperson != -1)
            {
                person1.setText(mapp.get(firstperson)?.getfirstName())

            }
            if(secondperson != -1)
            {
                person2.setText(mapp.get(secondperson)?.getfirstName())

            }

        }
        else
        {
            person1.setText(" ")
            person2.setText(" ")
        }
        if(firstperson != -1 && secondperson != -1)
        {
            val inflater: LayoutInflater = LayoutInflater.from(this)
            val subView: View = inflater.inflate(R.layout.transaction_info, null)
            var transactionsafe = false
            var balance1 :Int? = 0
            var balance2: Int? = 0
            var p1name:String? = " "
            var p2name:String? = " "
             balance2 = mapp.get(secondperson)?.getBalance()
             balance1 = mapp.get(firstperson)?.getBalance()
             p1name = mapp.get(firstperson)?.getfirstName()
             p2name = mapp.get(secondperson)?.getfirstName()

            subView.fromfield.text = p1name
            subView.to.text = p2name
            subView.balance1.text = balance1.toString()
            subView.balance2.text = balance2.toString()


            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val etamt = subView.findViewById<EditText>(R.id.amountentered)
            builder.setTitle("Transaction Details")
            builder.setView(subView)
             val customDialog = builder.create()




            //performing positive action
            builder.setPositiveButton("Approve", DialogInterface.OnClickListener { dialog, which ->
                run {

                    val amt: Int = etamt.text.toString().toInt()
                    if (amt != null) {
                        if (amt > balance1!!) {
                            Toast.makeText(
                                applicationContext,
                                "Please enter \n Valid integer amount",
                                Toast.LENGTH_LONG
                            ).show()
                            customDialog.dismiss()
                            updateLayout()
                        }
                        else
                            transactionsafe = true

                        if (transactionsafe) {
                            transferMoney(firstperson, secondperson, amt)
                            firstperson = -1
                            secondperson = -1
                            transactionsafe = false
                            updateLayout()
                            Toast.makeText(applicationContext, "Transaction Succcessful!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            //performing negative action
            builder.setNegativeButton("Cancel"){dialogInterface, which ->
                //Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
                firstperson = -1
                secondperson = -1
                person1.setText(" ")
                person2.setText(" ")
            }


            builder.show()
        }

    }

    private fun transferMoney(firstperson: Int, secondperson: Int, amt: Int) {
        var c1:Customer = mapp[firstperson]!!
        var c2:Customer = mapp[secondperson]!!
        c1.setBalance(-amt)
        c2.setBalance(+amt)
        //Toast.makeText(applicationContext,c1.getBalance().toString(), Toast.LENGTH_LONG).show()
        //Toast.makeText(applicationContext,c2.getBalance().toString(), Toast.LENGTH_LONG).show()
        helper.updateBalance(c1)
        helper.updateBalance(c2)
        //construct new arraylist from mappp and update adapter
        var tmplist = ArrayList<Customer>()
        for(i in mapp.values)
        {
            tmplist.add(i)

        }
        adapter.updateList(tmplist)
    }
}


