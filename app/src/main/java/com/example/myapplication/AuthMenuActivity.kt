package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AuthMenuActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authmenu)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.loginButton)

        loginButton.isEnabled = false

        emailEditText = findViewById(R.id.emailEditText)

        emailEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?,
                                       p1: Int, p2: Int, p3: Int) {
                if (p0.isValidEmail()){
                    emailEditText.error = null
                }else{
                    emailEditText.error = "https://www.youtube.com/watch?v=8YqGSQCwmDs"
                }
            }

            override fun afterTextChanged(p0: Editable?) { }
        })


        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                updateLoginButtonState()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun updateLoginButtonState() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val isEmailValid = email.isValidEmail()
        val isPasswordValid = password.isNotEmpty()

        loginButton.isEnabled = isEmailValid && isPasswordValid
    }

    fun onClickGoRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun onClickGoLogout(view: View) {
        val intent = Intent(this, LogOutPinActivity::class.java)
        startActivity(intent)
    }
}

fun CharSequence?.isValidEmail(): Boolean {
    return !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}