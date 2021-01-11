package com.example.flux.users

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flux.CartActivity
import com.example.flux.ProfileActivity
import com.example.flux.R
import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.where

class RealmIOQuery(context: Context) {
    private lateinit var realm :Realm


    init {
        Realm.init(context)
        realm=Realm.getDefaultInstance()
    }

   fun query (): Realm {
        return realm;
    }

    fun createUser(): Realm {
        return realm
    }
    private fun radioSwitcher(radioButton: Array<RadioButton>):String{
        for (button in radioButton){
            return if(button.isChecked){
                button.text.toString()
            }else {
                "Any"
            }
        }

        return ""
    }


   fun authorizeUser(email:String,password: String,users:Users): Users? {

         val query= realm.where(users::class.java).findAll();

           if (query != null) {
               for(user in query){

               if(user.getEmail() == email && password == user.getPassword()){

                   return user;
               }

               }


           }

       return null
  /*     var performIntent:Intent?
       if ((email == users.getEmail()) && (password == users.getPassword())) {
           realm.executeTransactionAsync{realm->
               val userEmail=realm.where(users::class.java).findFirstAsync().setEmail("email");
               val userPassword=realm.where(users::class.java).findFirstAsync().setEmail("password");
               if (userEmail.equals(email) && userPassword.equals(password)){
                   performIntent = Intent(context, ProfileActivity::class.java)
                   startActivity(performIntent)
               }else{
                   val toast = Toast.makeText(context,
                       "The email or password can not found",
                       Toast.LENGTH_LONG
                   )
                   toast.setGravity(Gravity.CENTER, 0, 0)
                   toast.show()
               }
           }
       }else{
           val toast = Toast.makeText(context,
               "The text field must not be left empty",
               Toast.LENGTH_LONG
           )
           toast.setGravity(Gravity.CENTER, 0, 0)
           toast.show()
       }
       password.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD*/
    }
    fun updateUser(users:Users){

        realm.executeTransactionAsync{realm->
            val count=realm.where(users::class.java).max("pKey");
            count?.toInt()?.plus(1)
            val user= realm.createObject(users::class.java,count)

        }
    }
    fun deleteUser(users:Users){

        realm.executeTransactionAsync{realm->
            val count=realm.where(users::class.java).max("pKey");
            count?.toInt()?.plus(1)
            val user= realm.createObject(users::class.java,count)

        }
    }
}



