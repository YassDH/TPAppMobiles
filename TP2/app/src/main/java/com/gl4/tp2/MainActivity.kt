package com.gl4.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }
    val rview : RecyclerView by lazy { findViewById(R.id.rview) }
    val editText = findViewById<EditText>(R.id.editText)
    lateinit var adapter : StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var matieres = listOf<String>("Cours","TP")
        var coursList = arrayOf<Student>(Student("Dhaouadi","Yassine","M"),Student("Yousfi","Wissem","M"),
            Student("Ben Salha","Mehdi", "F"), Student("Raboudi","Yassine","F"))
        var tpList = arrayOf<Student>(Student("Yousfi","Wissem","M"),
            Student("Ben Salha","Mehdi", "F"))

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,matieres)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val toast = Toast.makeText(applicationContext, matieres[position] , Toast.LENGTH_SHORT).show()
                if(matieres[position] == "Cours"){
                    adapter = StudentAdapter(coursList);
                    rview.adapter = adapter
                    rview.layoutManager = LinearLayoutManager(applicationContext)
                }else{
                    adapter = StudentAdapter(tpList);
                    rview.adapter = adapter
                    rview.layoutManager = LinearLayoutManager(applicationContext)
                }
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                val toast = Toast.makeText(applicationContext, "Not Selected" , Toast.LENGTH_SHORT).show()
                adapter = StudentAdapter(coursList);
                rview.adapter = adapter
                rview.layoutManager = LinearLayoutManager(applicationContext)
            }
        }

        editText.addTextChangedListener()
    }
}