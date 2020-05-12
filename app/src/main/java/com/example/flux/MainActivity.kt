package com.example.flux

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        wearCategoryListenerMen()
       // wearCategoryListenerWomen()
       // wearCategoryListenerChildren()
        //wearCategoryListenerSortBy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    private var color = 0
    private fun wearCategoryListenerMen(){

        val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)

        var category = arrayOf(findViewById<Button>(R.id.man),findViewById<Button>(R.id.women),
            findViewById<Button>(R.id.children),findViewById<Button>(R.id.sort_label))

        category.forEach { element->
            val text  =  element as TextView
            color = text.textColors.defaultColor

            element.setOnClickListener{
                category.forEach {
                    element->
                    element.setTextColor(color)
                }

                if(menVisibility.isVisible){
                    println(text.text)
                    if(text.text == getString(R.string.men)){
                        text.text = getString(R.string.men)

                    }else if(text.text == getString(R.string.women)){
                        text.text = getString(R.string.women)
                        println(text.text)
                    }else if(text.text == getString(R.string.children)){
                        text.text = getString(R.string.children)
                    }else if(text.text == getString(R.string.sort_by)){
                        text.text = getString(R.string.sort_by)
                    }
                    menVisibility.isVisible = false
                   

                }else{

                    if(text.text == getString(R.string.men)){
                        text.text = getString(R.string.men_triangle_up)

                    }else if(text.text == getString(R.string.women)){
                        text.text = getString(R.string.women_triangle_up)
                        println(text.text)
                    }else if(text.text == getString(R.string.children)){
                        text.text = getString(R.string.children_triangle_up)
                    }else if(text.text == getString(R.string.sort_by)){
                        text.text = getString(R.string.sort_triangle_up)
                    }
                    color = text.textColors.defaultColor
                    text.setTextColor(getColor(R.color.colorPrimaryDark))
                    menVisibility.isVisible = true
                }
            }
        }


    }
    private fun wearCategoryListenerWomen(){
        val men = findViewById<Button>(R.id.women)
        val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)
        val text  =  men as TextView

        men.setOnClickListener{
            if(menVisibility.isVisible){
                menVisibility.isVisible = false
                text.text = getString(R.string.women)
                text.setTextColor(color)

            }else    if(!menVisibility.isVisible){

                text.text = getString(R.string.women_triangle_up)
                color = text.textColors.defaultColor
                text.setTextColor(getColor(R.color.colorPrimaryDark))
                menVisibility.isVisible = true
            }
        }

    }
    private fun wearCategoryListenerChildren(){
        val men = findViewById<Button>(R.id.children)
        val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)
        val text  =  men as TextView

        men.setOnClickListener{
            if(menVisibility.isVisible){
                menVisibility.isVisible = false
                text.text = getString(R.string.children)
                text.setTextColor(color)

            }else    if(!menVisibility.isVisible){

                text.text = getString(R.string.children_triangle_up)
                color = text.textColors.defaultColor
                text.setTextColor(getColor(R.color.colorPrimaryDark))
                menVisibility.isVisible = true
            }
        }

    }

    private fun wearCategoryListenerSortBy(){
        val men = findViewById<Button>(R.id.sort_label)
        val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)
        val text  =  men as TextView

        men.setOnClickListener{
            if(menVisibility.isVisible){
                menVisibility.isVisible = false
                text.text = getString(R.string.sort_by)
                text.setTextColor(color)

            }else    if(!menVisibility.isVisible){

                text.text = getString(R.string.sort_triangle_up)
                color = text.textColors.defaultColor
                text.setTextColor(getColor(R.color.colorPrimaryDark))
                menVisibility.isVisible = true
            }
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
