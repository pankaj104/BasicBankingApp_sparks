package com.example.banking

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MyDBHelper(context: Context): SQLiteOpenHelper(context,"CUSTOMERS_DATA",null,1) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "CUSTOMERS_DATA"
        private const val TABLE_NAME = "CUSTOMERS"

        private const val CUSTOMER_ID = "cid"
        private const val FNAME = "FirstName"
        private const val LNAME = "LastName"
        private const val EMAIL = "email"
        private const val BALANCE = "balance"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CUSTOMERS(cid INTEGER PRIMARY KEY AUTOINCREMENT, FNAME TEXT, LNAME TEXT,EMAIL TEXT,BALANCE INTEGER)")

        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Pankaj','Kumar','pan3536@gmail.com',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Divyansh','Kushwaha','div2454@gmail.com',10000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Shikhar','Sarma','shikar2435@gmail.com',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Kishan','Raj','kishan323@gmail.com',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Sonam','Kapoor','sonam2353@gmail',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Drishti','kumari','drishti@yahoo.in',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Ananya','Verma','ananya2353@gmail.com',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Ankita','Sahni','Ankita2343@gamil.com',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Adarsh','Kumar','adarsh344@gmail.com',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Shrishti','Shukla','Shrishti2342@gmail.com',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Pahal','Beck','pahal354@gmail.com',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Jyoti','Singh','jyoti325@gmail.com',5000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Laksh','Singh','laksh2563@gmail.com',10000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Sanika','Khadkikar','sample@xyz',10000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Pahal','Jain','pahal4534@gmail.com',10000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Kumar','Ji','hitpalay3545@gmail.com.com',10000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Yash','Singh','yash2309@gmail.com',10000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Madan','Sharma','madan2349@gmail.com',10000)")
        db?.execSQL("INSERT INTO CUSTOMERS(FNAME,LNAME,EMAIL,BALANCE) VALUES('Atiksh','Verma','atiksh32645@gmail.com',10000)")


    }

    override fun onUpgrade(db:SQLiteDatabase, oldVersion: Int,newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun updateBalance(c: Customer) {
        val values = ContentValues()
        values.put(BALANCE, c.getBalance())

        val db: SQLiteDatabase = this.writableDatabase
        //val success = db.update(TABLE_CONTACTS, contentValues,"id="+emp.userId,null)
        val success = db.update(
            TABLE_NAME,
            values,
             " cid = " + c.getId(),
            null)

    }

}