package com.example.telefonskiimenik.screens.update_delete

import com.example.telefonskiimenik.services.DatabaseHelper


class UpdateDeleteContract {

    interface View {
        fun getDatabaseHelper(): DatabaseHelper
        fun onUpdateSuccess()
        fun onUpdateError()
        fun onDeleteSuccess()
        fun onDeleteError()
    }

    interface Presenter {
        fun registerActivity(activityInstance: UpdateDeleteContract.View)
        fun updateData(id: String, firstName: String, lastName: String, dateOfBirth: String, email: String)
        fun deleteData(id: String)
    }
}