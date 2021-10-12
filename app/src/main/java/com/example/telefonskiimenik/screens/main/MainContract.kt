package com.example.telefonskiimenik.screens.main

import com.example.telefonskiimenik.models.Person
import com.example.telefonskiimenik.services.DatabaseHelper

class MainContract {

    interface View {
        fun getDatabaseHelper(): DatabaseHelper
        fun dataRetrievedSuccessfully(listData: ArrayList<Person>)
    }

    interface Presenter {
        fun registerActivity(activityInstance: MainContract.View)
        fun getData(columnName: String, value: String)
    }
}