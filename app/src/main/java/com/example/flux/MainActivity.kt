package com.example.flux

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.*
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray
import org.json.JSONObject
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
        val isMeant=fetchItems(this)
        if(isMeant){

                    println(storeDB.length())
        var configItems = arrayOf("  Public Transit", "  Cycling", "  Relics Maker")
                  val category = CategoryAdaptor(this,storeDB)

                 wears_category_items_view.adapter= category



        }


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
        val register =menu.findItem(R.id.register)
        val registerText = SpannableString(register.title);
        registerText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, registerText.length, 0);
        register.title = registerText


        val signIn=menu.findItem(R.id.sign_in)
        val signText = SpannableString(signIn.title);
        signText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, signText.length, 0);
        signIn.title = signText

        val help=menu.findItem(R.id.help_item)
        val helpText = SpannableString(help.title);
        helpText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, helpText.length, 0);
        help.title = helpText

        val about=menu.findItem(R.id.about_item)
        val aboutText = SpannableString(about.title);
        aboutText.setSpan( ForegroundColorSpan(getColor(R.color.colorAccentWhite)), 0, aboutText.length, 0);
        about.title = aboutText
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        var performEventOn : Intent?
        val itemId= item.itemId


        if ( itemId== R.id.sign_in){

            performEventOn= Intent(this, LoginActivity::class.java)
            startActivity(performEventOn)

        }
        if (itemId == R.id.sign_in){
            performEventOn= Intent(this, RegisterActivity::class.java)
            startActivity(performEventOn)
        }

        if (itemId == R.id.help_item){
            performEventOn= Intent(this, HelpActivity::class.java)
            startActivity(performEventOn)
        }

        if (itemId == R.id.about_item){
            val toast = Toast.makeText(applicationContext,R.string.about_message
                , Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.view.background.setTint(getColor(R.color.colorAccent))
            var text =  toast.view.findViewById<TextView>(android.R.id.message);
            text.setTextColor(getColor(R.color.colorAccentWhite));
            toast.show()
        }
        return super.onOptionsItemSelected(item)

        return super.onOptionsItemSelected(item)
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
    private fun wearCategoryListenerAddToCart(){
        val addToCart = findViewById<Button>(R.id.grid_add_to_cart)
        val  menVisibility =  findViewById<ConstraintLayout>(R.id.wear_category_option)


    }
    protected var  storeDB =  JSONArray()
    protected fun fetchItems(context: Activity): Boolean{
        val parser=FileParser()
       val store = parser.parseFile(context, R.raw.new_mvc)


            storeDB=store

        //storeDB.set(0,store);
      return  true
    }
    class CategoryAdaptor(private var context:Activity,var storeDb: JSONArray) :
        BaseAdapter() {



        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val layout = context.layoutInflater

            val view =layout.inflate(R.layout.grid_resource,null,true)
            val gridItemView = view.findViewById<ImageView>(R.id.grid_item)
            var gridview = context.findViewById<GridView>(R.id.wears_category_items_view)
            val gridItemCart = view.findViewById<Button>(R.id.grid_add_to_cart)
            val gridItemColor = view.findViewById<Button>(R.id.grid_item_color)
            val gridItemZoom = view.findViewById<Button>(R.id.grid_zoom_item)
            var handler = Handler()


            /*
            var imageUrl = URL("http://whc.unesco.org//uploads/thumbs/site_1485_0002-750-0-20150610150743.jpg")
            val conn: HttpURLConnection = imageUrl.openConnection() as HttpURLConnection
            conn.doInput = true
            conn.connect()
            val stream: InputStream = conn.inputStream
            val bitmap = BitmapFactory.decodeStream(stream)*/

            val url=JSONObject(storeDb[position].toString()).getString("image_url_4x").replace("http","https")

            Picasso.with(context).load(url).fetch(object : Callback {
                override fun onSuccess() {

                    val bitmap = Picasso.with(context).load(url).into(gridItemView)
                }

                override fun onError() {

                }
            })


            gridItemCart.setBackgroundResource(R.drawable.ic_shopping_cart_black_afterdp)
            gridItemColor.setBackgroundResource(R.drawable.ic_color_lens_black_24dp)
            gridItemZoom.setBackgroundResource(R.drawable.ic_zoom_in_black_24dp)

            return view
            // return super.getView(position, convertView, parent)
        }

        override fun getItem(position: Int): Any? {
          return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
           return  storeDb.length()
        }


    }

}

