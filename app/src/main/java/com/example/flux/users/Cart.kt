package com.example.flux.users

import android.os.Bundle
import android.os.Parcel
import android.widget.ImageView




class Cart(){

    private val cart :  ArrayList<ImageView> = arrayListOf()
    private  var itemName : ArrayList<String> = arrayListOf()


    public fun addToCart(item: ImageView){
        cart.add(item)

    }

    public fun getCartItems(): ArrayList<ImageView> {
        return  cart
    }


    public fun addItemName(name: String){
        itemName.add(name)

    }
    companion object{




    }
    init {
        this.getCartItems()
    }
    public  fun  getItemName(): ArrayList<String> {
        return  itemName
    }






}