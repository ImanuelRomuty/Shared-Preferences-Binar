package com.example.sharedpreferencesample

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var saveButton : Button
    private lateinit var viewButton : Button
    private lateinit var clearButton : Button
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var nameTextView: TextView
    private lateinit var idTextView: TextView

    private val sharedPrefile = "kotlinsharedpreference"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveButton = findViewById(R.id.btnSave)
        viewButton = findViewById(R.id.btnView)
        clearButton = findViewById(R.id.btnClear)
        nameEditText = findViewById(R.id.etInputName)
        idEditText = findViewById(R.id.etInputId)
        nameTextView = findViewById(R.id.tvShowName)
        idTextView = findViewById(R.id.tvShowId)


        val sharedPreferences : SharedPreferences = this.getSharedPreferences(sharedPrefile, Context.MODE_PRIVATE)



        saveButton.setOnClickListener {
            val id : Int = Integer.parseInt(idEditText.text.toString())
            val name : String = nameEditText.text.toString()
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key",id)
            editor.putString("name_key",name)
            editor.apply()
            Toast.makeText(this , "SAVED" , Toast.LENGTH_LONG).show()

        }
        viewButton.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key","defaultName")
            if (sharedIdValue == 0 && sharedNameValue == "defaultName"){
                nameTextView.text = "default nane : $sharedNameValue"
                idTextView.text = "default bane : $sharedIdValue"
//                nameTextView.setText("default bane : $sharedNameValue").toString()
//                idTextView.setText("default bane : $sharedIdValue.toString()")
                Toast.makeText(this , "Data Kosong" , Toast.LENGTH_LONG).show()
            }else{
                nameTextView.text = sharedNameValue.toString()
//                nameTextView.setText(sharedNameValue).toString()
                idTextView.text = sharedIdValue.toString()
//                idTextView.setText(sharedIdValue).toString()
                Toast.makeText(this , "Data View Ditampilkan" , Toast.LENGTH_LONG).show()
            }
        }
        clearButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            nameTextView.setText("")
            idTextView.setText("")
            Toast.makeText(this , "Data clear" , Toast.LENGTH_LONG).show()
        }
    }
}