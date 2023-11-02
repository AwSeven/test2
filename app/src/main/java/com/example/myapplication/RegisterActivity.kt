package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.admin.AdminUserBuilder
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var emailEditText1: EditText
    private lateinit var passwordEditText1: EditText
    private lateinit var registerButton1: Button
    private lateinit var loginButton1: Button
    var client: SupabaseClient? = null
    var pass: String = ""
    var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        emailEditText1 = findViewById(R.id.emailEditText1)
        passwordEditText1 = findViewById(R.id.passwordEditText1)
        registerButton1 = findViewById(R.id.registerButton1)
        loginButton1 = findViewById(R.id.loginButton1)

        loginButton1.isEnabled = false

        emailEditText1 = findViewById(R.id.emailEditText1)
        client = createSupabaseClient(
            supabaseUrl = "https://znyrxubidffhaynvpbgw.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InpueXJ4dWJpZGZmaGF5bnZwYmd3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTg1NzI2NjEsImV4cCI6MjAxNDE0ODY2MX0.opIKlRjSjSQX_CdrJ5JCz8c7lyzQjaCcnki1yaF9GWI",
        ){
            install(Postgrest)
            install(GoTrue)
        }



        emailEditText1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?,
                                       p1: Int, p2: Int, p3: Int) {
                if (p0.isValidEmail1()){
                    emailEditText1.error = null
                }else{
                    emailEditText1.error = "https://www.youtube.com/watch?v=8YqGSQCwmDs"
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                email = emailEditText1.text.toString()
            }
        })


        passwordEditText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateLoginButtonState()
            }

            override fun afterTextChanged(p0: Editable?) {
                pass = passwordEditText1.text.toString()
            }
        })
    }

    private fun updateLoginButtonState() {
        val email = emailEditText1.text.toString()
        val password = passwordEditText1.text.toString()

        val isEmailValid = email.isValidEmail1()
        val isPasswordValid = password.isNotEmpty()

        loginButton1.isEnabled = isEmailValid && isPasswordValid
    }

    fun onClickGoReg(view: View) {
        lifecycleScope.launch {
            val city = client!!.postgrest["auth.users"].select()
            Log.e("!", city.body.toString())
            val user = client!!.gotrue.signUpWith(Email) {
                email = email
                password = pass
            }
        }
        val intent = Intent(this, CreatePinActivity::class.java)
        startActivity(intent)
    }

    fun onClickGoIn(view: View) {
        val intent = Intent(this, AuthMenuActivity::class.java)
        startActivity(intent)
    }
    private fun getClient() {


    }
}

fun CharSequence?.isValidEmail1(): Boolean {
    return !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}