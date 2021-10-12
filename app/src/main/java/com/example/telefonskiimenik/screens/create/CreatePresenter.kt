package com.example.telefonskiimenik.screens.create

class CreatePresenter: CreateContract.Presenter {

    private lateinit var interfaceToCreateActivity: CreateContract.View

    /**
     * Public methods
     */
    override fun registerActivity(activityInstance: CreateContract.View) {
        interfaceToCreateActivity = activityInstance
    }

    override fun addData(firstName: String, lastName: String, dateOfBirth: String, email: String) {
        val databaseHelper = interfaceToCreateActivity.getDatabaseHelper()
        val insertData = databaseHelper.addData(firstName, lastName, dateOfBirth, email)
        if (insertData)
            interfaceToCreateActivity.insertDataSuccess()
        if (!insertData)
            interfaceToCreateActivity.insertDataError()
    }
}