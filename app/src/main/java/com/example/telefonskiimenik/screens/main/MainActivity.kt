package com.example.telefonskiimenik.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.telefonskiimenik.R
import com.example.telefonskiimenik.base.BaseActivity
import com.example.telefonskiimenik.models.Person
import com.example.telefonskiimenik.screens.create.CreateActivity
import com.example.telefonskiimenik.screens.main.adapter.ItemClickListener
import com.example.telefonskiimenik.screens.main.adapter.PersonsRecyclerViewAdapter
import com.example.telefonskiimenik.screens.update_delete.UpdateDeleteActivity
import com.example.telefonskiimenik.services.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity: BaseActivity(), MainContract.View, ItemClickListener {

    companion object {
        const val update_delete_person = "UPDATE_DELETE_PERSON"
    }

    private val mainPresenter: MainPresenter by inject()
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var personsRecyclerViewAdapter: PersonsRecyclerViewAdapter
    private var spinnerItemSelected = ""

    /**
     * Lifecycle methods
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setScreenTitle(getString(R.string.main_screen_title))
        databaseHelper = DatabaseHelper(this)
        mainPresenter.registerActivity(this)
        setupView()
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.getData(spinnerItemSelected, edit_text_search.text.toString())
    }

    /**
     * Private methods
     */
    private fun setupView() {
        spinnerSetup()
        setupRecyclerViewAdapter()
        button_new.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        button_search.setOnClickListener {
            mainPresenter.getData(spinnerItemSelected, edit_text_search.text.toString())
        }
    }

    private fun spinnerSetup() {
        val arrayAdapter = ArrayAdapter.createFromResource(this, R.array.main_screen_search_by, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_search_by_options.adapter = arrayAdapter
        spinner_search_by_options.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                spinnerItemSelected = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    /**
     * Public methods
     */
    override fun getDatabaseHelper(): DatabaseHelper {
        return databaseHelper
    }

    override fun dataRetrievedSuccessfully(listData: ArrayList<Person>) {
        personsRecyclerViewAdapter.addPersons(listData)
    }

    /**
     * Private methods
     */
    private fun setupRecyclerViewAdapter() {
        personsRecyclerViewAdapter = PersonsRecyclerViewAdapter(this)
        recycler_view_results.layoutManager = LinearLayoutManager(this)
        recycler_view_results.adapter = personsRecyclerViewAdapter
    }

    override fun onItemClick(person: Person) {
        val intent = Intent(this, UpdateDeleteActivity::class.java)
        intent.putExtra(update_delete_person, person)
        startActivity(intent)
    }
}