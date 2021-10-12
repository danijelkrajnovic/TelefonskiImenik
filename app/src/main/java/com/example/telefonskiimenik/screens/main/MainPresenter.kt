package com.example.telefonskiimenik.screens.main

import com.example.telefonskiimenik.models.Person


class MainPresenter: MainContract.Presenter {

    private lateinit var interfaceToMainActivity: MainContract.View

    /**
     * Public methods
     */
    override fun registerActivity(activityInstance: MainContract.View) {
        interfaceToMainActivity = activityInstance
    }

    override fun getData(columnName: String, value: String) {
        val databaseHelper = interfaceToMainActivity.getDatabaseHelper()
        val data = databaseHelper.getData(columnName, value)
        val listData = ArrayList<Person>()
        while (data.moveToNext()) {
            listData.add(Person(
                data.getString(0),
                data.getString(1),
                data.getString(2),
                data.getString(3),
                data.getString(4),
            ))
        }
        interfaceToMainActivity.dataRetrievedSuccessfully(listData)
    }


}
