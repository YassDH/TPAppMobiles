package com.gl4.tp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale


class StudentAdapter (private val data : ArrayList<Student>) : RecyclerView.Adapter<StudentAdapter.ViewHolder>(), Filterable{

    var dataFilterList : ArrayList<Student> = data
    var currentFilterState = 0;
    private var currentFilter: Filter = Filter1()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.StudentName)
        val image : ImageView = itemView.findViewById(R.id.imageView)
        val present : CheckBox = itemView.findViewById(R.id.present)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = dataFilterList[position].nom+" "+dataFilterList[position].prenom
        if(dataFilterList[position].genre == "M"){
            holder.image.setImageResource(R.drawable.men)
        }else{
            holder.image.setImageResource(R.drawable.female)
        }
        holder.present.isChecked = dataFilterList[position].present

        holder.present.setOnCheckedChangeListener{ view, value ->
            if(position < dataFilterList.size){
                data[dataFilterList[position].id-1].present = value
                if(currentFilterState == 0)
                    filter.filter("all")
                else if(currentFilterState == 1)
                    filter.filter("present")
                else if(currentFilterState == 2)
                    filter.filter("absent")
            }
        }
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    override fun getFilter(): Filter {
        return currentFilter
    }
    private inner class Filter1() : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charSearch = constraint.toString()
            if(charSearch.isEmpty()) {
                if(currentFilterState == 0)
                    dataFilterList = data
                else if(currentFilterState == 1)
                    dataFilterList = data.filter { s -> s.present == true } as ArrayList<Student>
                else if(currentFilterState == 2)
                    dataFilterList = data.filter { s -> s.present == false } as ArrayList<Student>

            } else {
                var toSearchInList = ArrayList<Student>()

                if(currentFilterState == 0)
                    toSearchInList = data
                else if(currentFilterState == 1)
                    toSearchInList = data.filter { s -> s.present == true } as ArrayList<Student>
                else if(currentFilterState == 2)
                    toSearchInList = data.filter { s -> s.present == false } as ArrayList<Student>

                val resultList = ArrayList<Student>()
                for (student in toSearchInList) {
                    val name = student.nom+" "+student.prenom
                    if (name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                        resultList.add(student)
                    }
                }
                dataFilterList = resultList
            }
            val filterResults = FilterResults()
            filterResults.values = dataFilterList
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            dataFilterList = results?.values as ArrayList<Student>
            notifyDataSetChanged()
        }
    }

    private inner class Filter2() : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val listToShow = constraint.toString()
            if(listToShow == "present"){
                val presentStudents = data.filter { s -> s.present == true } as ArrayList<Student>
                dataFilterList = presentStudents
                currentFilterState = 1
            }else if (listToShow == "absent"){
                val absentStudents = data.filter { s -> s.present == false } as ArrayList<Student>
                dataFilterList = absentStudents
                currentFilterState = 2
            }
            else{
                dataFilterList = data
                currentFilterState = 0
            }
            val results = FilterResults()
            results.values = dataFilterList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            dataFilterList = results?.values as ArrayList<Student>
            notifyDataSetChanged()
        }
    }
    fun switchToFilter1() {
        currentFilter = Filter1()
        notifyDataSetChanged()
    }
    fun switchToFilter2() {
        currentFilter = Filter2()
        notifyDataSetChanged()
    }
}