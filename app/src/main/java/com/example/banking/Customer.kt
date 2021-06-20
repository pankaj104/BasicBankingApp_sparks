package com.example.banking

class Customer {
   private var id:Int = 0
   private var balance:Int = 0
   private val firstname: String
   private val lastname: String
   private val email: String

   constructor(idd: Int,fname: String, lname: String,e: String,b:Int) {
      firstname = fname
      lastname = lname
      id = idd
      balance = b
      email = e
   }

   fun getId(): Int {
      return id
   }

   fun getBalance(): Int{
      return balance
   }

   fun setBalance(amt: Int){
      balance +=  amt
   }

   fun getfirstName(): String {
      return firstname
   }

   fun getlastName(): String {
      return lastname
   }

   fun getEmail(): String{
      return email
   }

   fun getFullName(): String {
      return firstname+ " " + lastname
   }
}