package com.example.telefonskiimenik.screens.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.telefonskiimenik.R
import com.example.telefonskiimenik.models.Person

class PersonsRecyclerViewAdapter(private val itemClickListener: ItemClickListener): RecyclerView.Adapter<PersonsRecyclerViewAdapter.CustomViewHolder>() {

    private val personsList = mutableListOf<Person>()

    inner class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val itemContainer: LinearLayout = view.findViewById(R.id.recycler_view_item_container)
        val firstNameTextView: TextView = view.findViewById(R.id.recycler_view_first_name)
        val lastNameTextView: TextView = view.findViewById(R.id.recycler_view_last_name)
        val dateOfBirthTextView: TextView = view.findViewById(R.id.recycler_view_date_of_birth)
        val emailTextView: TextView = view.findViewById(R.id.recycler_view_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val periodRow = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_view_adapter, parent, false)
        return CustomViewHolder(periodRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val person = personsList[position]
        holder.firstNameTextView.text = person.firstName
        holder.lastNameTextView.text = person.lastName
        holder.dateOfBirthTextView.text = person.dateOfBirth
        holder.emailTextView.text = person.email

        holder.itemContainer.setOnClickListener {
            itemClickListener.onItemClick(person)
        }
    }

    override fun getItemCount(): Int {
        return personsList.size
    }

    /**
     * Public methods
     */
    fun addPersons(items: ArrayList<Person>) {
        personsList.clear()
        personsList.addAll(items)
        notifyDataSetChanged()
    }

    fun getPersons() = personsList

}