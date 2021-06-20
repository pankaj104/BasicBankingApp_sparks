package com.example.banking

class Model {

    private val name1: String
    private val name2: String


    constructor(name11: String, name22: String) {
        name1 = name11
        name2= name22

    }



    fun getName1(): String {
        return name1
    }

    fun getName2(): String {
        return name2
    }


}