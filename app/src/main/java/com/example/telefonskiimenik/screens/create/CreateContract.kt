package com.example.telefonskiimenik.screens.create

import com.example.telefonskiimenik.services.DatabaseHelper

class CreateContract {

    interface View {
        fun getDatabaseHelper(): DatabaseHelper
        fun insertDataSuccess()
        fun insertDataError()
    }

    interface Presenter {
        fun registerActivity(activityInstance: CreateContract.View)
        fun addData(firstName: String, lastName: String, dateOfBirth: String, email: String)
    }
}