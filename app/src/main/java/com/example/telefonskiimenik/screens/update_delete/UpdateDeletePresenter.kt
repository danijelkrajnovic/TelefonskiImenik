package com.example.telefonskiimenik.screens.update_delete


class UpdateDeletePresenter: UpdateDeleteContract.Presenter {

    private lateinit var interfaceToUpdateDeleteActivity: UpdateDeleteContract.View

    /**
     * Public methods
     */
    override fun registerActivity(activityInstance: UpdateDeleteContract.View) {
        interfaceToUpdateDeleteActivity = activityInstance
    }

    override fun updateData(id: String, firstName: String, lastName: String, dateOfBirth: String, email: String) {
        val databaseHelper = interfaceToUpdateDeleteActivity.getDatabaseHelper()
        val updateData = databaseHelper.updateData(id, firstName, lastName, dateOfBirth, email)
        if (updateData)
            interfaceToUpdateDeleteActivity.onUpdateSuccess()
        if (!updateData)
            interfaceToUpdateDeleteActivity.onUpdateError()
    }

    override fun deleteData(id: String) {
        val databaseHelper = interfaceToUpdateDeleteActivity.getDatabaseHelper()
        val deleteData = databaseHelper.deleteData(id)
        if (deleteData)
            interfaceToUpdateDeleteActivity.onDeleteSuccess()
        if (!deleteData)
            interfaceToUpdateDeleteActivity.onDeleteError()
    }

}