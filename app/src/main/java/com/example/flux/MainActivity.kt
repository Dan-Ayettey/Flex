package com.example.flux

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.grid_resource.*
import org.json.JSONArray
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }




        wearCategoryListener()
        val category =CategoryAdaptor(this,45, arrayListOf(3,4,56,4,5,6,7,8,4,5,4,5,76,6,4,34,34,34,5,34,6,67,8), arrayOf(2,49,45,0))

        wears_category_items_view.adapter= category

    }


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
            //your codes here
        }
        return super.onCreateView(name, context, attrs)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    private var color = 0
    private fun wearCategoryListener(){

        val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)

        var category = arrayOf(findViewById<Button>(R.id.man),findViewById<Button>(R.id.women),
            findViewById<Button>(R.id.children),findViewById<Button>(R.id.sort_label))

        category.forEach { element->
            val text  =  element as TextView
            color = text.textColors.defaultColor

            element.setOnClickListener{
                val fadeOut: Animation = AlphaAnimation(1.0f, 0.0f)
                val fadeIn: Animation = AlphaAnimation(0.0f, 0.1f)
                category.forEach {
                    element->
                    element.setTextColor(color)
                    element.text= element.text.toString().replace(getString(R.string.after_symbol),getString(R.string.init_symbol))

                }

                if(menVisibility.isVisible){

                    when (text.text) {
                        getString(R.string.men_triangle_up) -> {
                            text.text = getString(R.string.men)
                        }
                        getString(R.string.women_triangle_up) -> {
                            text.text = getString(R.string.women)
                        }
                        getString(R.string.children_triangle_up) -> {
                            text.text = getString(R.string.children)
                        }
                        getString(R.string.sort_triangle_up) -> {
                            text.text = getString(R.string.sort_by)
                        }
                    }

                    menVisibility.animation=fadeOut

                    menVisibility.animation.duration = 200
                     menVisibility.isVisible = false


                }else {

                    when (text.text) {
                        getString(R.string.men) -> {
                            text.text = getString(R.string.men_triangle_up)

                        }
                        getString(R.string.women) -> {
                            text.text = getString(R.string.women_triangle_up)
                            println(text.text)
                        }
                        getString(R.string.children) -> {
                            text.text = getString(R.string.children_triangle_up)
                        }
                        getString(R.string.sort_by) -> {
                            text.text = getString(R.string.sort_triangle_up)
                        }
                    }
                    color = text.textColors.defaultColor
                    text.setTextColor(getColor(R.color.colorPrimaryDark))
                    menVisibility.animation=fadeIn

                    menVisibility.animation.duration = 200
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

    class CategoryAdaptor(private  var context:Activity,private var desc:Int, private  var item:ArrayList<Int>, private  var imgX: Array<Int>) :
        ArrayAdapter<Int>(context,desc,item) {
        private var imageView = ImageView(context)
        var  storeDB = JSONArray()
        private fun queryForRelics(){
            val parser=FileParser()
            storeDB = parser.parseFile(context, R.raw.new_mvc)
            for (artifact in arrayOf(storeDB)){
                storeDB = artifact;
            }

        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            queryForRelics()
            var layout = context.layoutInflater

            var view =layout.inflate(R.layout.grid_resource,null,true)
            var gridItemView = view.findViewById<ImageView>(R.id.grid_item)
            var gridItemCart = view.findViewById<Button>(R.id.grid_add_to_cart)

            val url = URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464")
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())



            if (convertView == null) {

              //  gridItemView .layoutParams = ConstraintLayout.LayoutParams(580,600 )
            } else {
//               gridItemView = convertView as ImageView
            }
            gridItemView.setImageBitmap(bmp)
            return view
            // return super.getView(position, convertView, parent)
        }


    }

}

