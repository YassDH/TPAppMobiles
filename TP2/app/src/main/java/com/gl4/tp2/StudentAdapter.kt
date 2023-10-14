package com.gl4.tp2

import android.R.attr.x
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale


class StudentAdapter (private val data : Array<Student>) : RecyclerView.Adapter<StudentAdapter.ViewHolder>(), Filterable {
    lateinit var dataFilterList : Array<Student>
    init {
        dataFilterList = data
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.StudentName)
        val image : ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = data[position].nom+" "+data[position].prenom
        if(data[position].genre == "M"){
            holder.image.setImageResource(R.drawable.men)
        }else{
            holder.image.setImageResource(R.drawable.female)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = data
                } else {
                    val resultList = arrayOf<Student>()
                    for (student in data) {
                        if (student.nom.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.plus(student)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as Array<Student>
                notifyDataSetChanged()
            }

        }
    }

}