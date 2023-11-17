package com.gl4.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }
    val rview : RecyclerView by lazy { findViewById(R.id.rview) }
    val editText : EditText  by lazy { findViewById(R.id.searchStudent) }
    val checkBox1 : RadioButton by lazy { findViewById(R.id.allStudents) }
    val checkBox2 : RadioButton  by lazy { findViewById(R.id.presentStudents) }
    val checkBox3 : RadioButton  by lazy { findViewById(R.id.absentStudents) }

    lateinit var adapter : StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var students = ArrayList<Student>()
        adapter = StudentAdapter(students);

        var matieres = listOf<String>(
            "Cours",
            "TP"
        )
        var coursList = arrayListOf<Student>(
            Student(1,"Dhaouadi","Yassine","M", true),
            Student(2,"Yousfi","Wissem","M", false),
            Student(3,"Ben Salha","Mehdi", "F", true),
            Student(4,"Raboudi","Yassine","F", false)
        )
        var tpList = arrayListOf<Student>(
            Student(1,"Yousfi","Wissem","M", false),
            Student(2,"Ben Salha","Mehdi", "F", true)
        )

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,matieres)

        rview.layoutManager = LinearLayoutManager(this.applicationContext)
        rview.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val toast = Toast.makeText(applicationContext, matieres[position] , Toast.LENGTH_SHORT).show()
                if(matieres[position] == "Cours"){
                    students.clear()
                    students.addAll(coursList)
                    rview.adapter?.notifyDataSetChanged()
                    adapter.switchToFilter1();
                    adapter.filter.filter("");
                }else{
                    students.clear()
                    students.addAll(tpList)
                    rview.adapter?.notifyDataSetChanged()
                    adapter.switchToFilter1();
                    adapter.filter.filter("");
                }
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                val toast = Toast.makeText(applicationContext, "Not Selected" , Toast.LENGTH_SHORT).show()
                students.clear()
                students.addAll(coursList)
                rview.adapter?.notifyDataSetChanged()
                adapter.switchToFilter1();
                adapter.filter.filter("");
            }
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.switchToFilter1();
                adapter.filter.filter(s);
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        checkBox1.setOnCheckedChangeListener{view , value ->
            if(value){
                adapter.switchToFilter2()
                adapter.filter.filter("all");
            }
        }

        checkBox2.setOnCheckedChangeListener{view , value ->
            if(value){
                adapter.switchToFilter2()
                adapter.filter.filter("present");
            }
        }
        checkBox3.setOnCheckedChangeListener{ view , value ->
            if(value){
                adapter.switchToFilter2()
                adapter.filter.filter("absent");
            }
        }
    }
}