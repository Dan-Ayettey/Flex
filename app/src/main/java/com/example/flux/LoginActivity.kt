package com.example.flux

import android.content.ClipData
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

import com.example.flux.users.RealmIOQuery
import com.example.flux.users.UserBuilder
import com.example.flux.users.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import java.io.SerializablePermission
import java.util.*
import kotlin.collections.ArrayList


class LoginActivity : AppCompatActivity() {
    private var profileTemp= ArrayList<String>()
    private var routeIntent : Intent= Intent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        this.title = getString(R.string.login)
        signIn()

    }

    private fun signIn(){
        val sign =findViewById<Button>(R.id.sign_in)
        val email=findViewById<EditText>(R.id.email)
        val password=findViewById<EditText>(R.id.password)
        val firstName=findViewById<EditText>(R.id.first_name)
        val lastName=findViewById<EditText>(R.id.sure_name)
        val database= FirebaseDatabase.getInstance()
        val databaseReference=database.reference
        val queryProvider= UserBuilder()
         queryProvider.pullUser(this)

        sign.setOnClickListener {
            val realm = RealmIOQuery(this)
            val user=Users()
            password.inputType=InputType.TYPE_TEXT_VARIATION_NORMAL
            val users = realm.authorizeUser(email.text.toString(),password.text.toString(),user)
            password.inputType=InputType.TYPE_TEXT_VARIATION_PASSWORD
            if(users !=null){
               Intent(this@LoginActivity,ProfileActivity::class.java)
                routeIntent = Intent(this@LoginActivity,ProfileActivity::class.java)
                profileTemp.add(users.getEmail())
                profileTemp.add(users.getFirstName().plus(""))
                routeIntent.putExtra("USERS",profileTemp);
                startActivity( routeIntent )

            }else{
                val toast=Toast.makeText(this@LoginActivity,"The account does not exist in out database",Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()

            }

        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Inflate the menu; this adds items to the action bar if it is present.

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
        if (itemId == R.id.register){
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
            val text =  toast.view.findViewById<TextView>(android.R.id.message);
            text.setTextColor(getColor(R.color.colorAccentWhite));
            toast.show()
        }
        return super.onOptionsItemSelected(item)


    }

}
