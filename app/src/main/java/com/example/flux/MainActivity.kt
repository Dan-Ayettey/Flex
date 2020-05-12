package com.example.flux

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
        wearCategoryListenerWomen()
        wearCategoryListenerChildren()
        wearCategoryListenerSortBy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    private var color = 0
    private fun wearCategoryListenerMen(){
        val men = findViewById<Button>(R.id.men_label)
        val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)
        val text  =  men as TextView

        men.setOnClickListener{
            if(menVisibility.isVisible){
                menVisibility.isVisible = false
                text.text = getString(R.string.men)
                text.setTextColor(color)

            }else    if(!menVisibility.isVisible){

                text.text = getString(R.string.men_triangle_up)
                color = text.textColors.defaultColor
                text.setTextColor(getColor(R.color.colorPrimaryDark))
                menVisibility.isVisible = true
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
