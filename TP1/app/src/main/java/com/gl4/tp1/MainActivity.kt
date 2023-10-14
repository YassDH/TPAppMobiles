package com.gl4.tp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var txtEmail : EditText
    lateinit var txtPassword : EditText
    lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        txtEmail = findViewById(R.id.EmailInput)
        txtPassword = findViewById(R.id.PasswordInput)
        btnLogin = findViewById(R.id.connexion)

        btnLogin.setOnClickListener { view ->
            // Retrieve the email when the button is clicked
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()

            /*
            lateinit var toast : Toast
            if(email == "gl4@insat.tn" && password =="insat2022"){
                toast = Toast.makeText(this, "Sucess !" , Toast.LENGTH_SHORT)
                val intent = Intent(view.context,WelcomeActivity::class.java)
                intent.putExtra("email",email)
                startActivity(intent)
            }else{
                toast = Toast.makeText(this, "Email : $email ou mot de passe : $password sont inccorrects !" , Toast.LENGTH_SHORT)
            }
            toast.show()
             */

            val intent = Intent(view.context,ImagePicker::class.java)
            startActivity(intent)

        }
    }
}