package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address)

        val editText = findViewById<EditText>(R.id.editText)
        val sendDataButton = findViewById<Button>(R.id.sendDataButton)
            sendDataButton.isEnabled = false
        val pattern = "[г|Г]. [А-Яа-я]+, [у|У]л. [А-Яа-я]+, [д|Д]. [0-9]+".toRegex()

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                val isMatched = pattern.matches(input)
                if (isMatched) {
                    editText.error = null
                } else {
                    editText.error = "Введите адрес в формате ' г. Название города, ул. Название улицы, д.'"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        sendDataButton.setOnClickListener {
            val data = editText.text.toString()
            val intent = Intent(this, MainPageActivity::class.java)
            intent.putExtra("key", data)
            startActivity(intent)
        }
    }
}