package com.example.telefonskiimenik.screens.create

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import com.example.telefonskiimenik.R
import com.example.telefonskiimenik.base.BaseActivity
import com.example.telefonskiimenik.services.DatabaseHelper
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.activity_update_delete.*
import org.koin.android.ext.android.inject

class CreateActivity: BaseActivity(), CreateContract.View {

    private val createPresenter: CreatePresenter by inject()
    private lateinit var databaseHelper: DatabaseHelper

    /**
     * Lifecycle methods
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        setScreenTitle(getString(R.string.create_screen_title))
        databaseHelper = DatabaseHelper(this)
        createPresenter.registerActivity(this)
        setupView()
    }

    /**
     * Private methods
     */
    private fun setupView() {
        button_save.setOnClickListener {
            val firstName = edit_text_first_name.text.toString()
            val lastName = edit_text_last_name.text.toString()
            val dateOfBirth = edit_text_date_of_birth.text.toString()
            val email = edit_text_email.text.toString()

            var fieldsError = false
            var errorMessage = getString(R.string.create_screen_fields_error_message)
            if (firstName.length > DatabaseHelper.FIRST_NAME_LENGTH) {
                errorMessage += getString(R.string.create_screen_fields_error_message_first_name)
                fieldsError = true
            }
            if (lastName.length > DatabaseHelper.LAST_NAME_LENGTH) {
                errorMessage += getString(R.string.create_screen_fields_error_message_last_name)
                fieldsError = true
            }
            if (dateOfBirth.length > DatabaseHelper.DATE_OF_BIRTH_LENGTH) {
                errorMessage += getString(R.string.create_screen_fields_error_message_date_of_birth)
                fieldsError = true
            }
            if (email.length > DatabaseHelper.EMAIL_LENGTH) {
                errorMessage += getString(R.string.create_screen_fields_error_message_email)
                fieldsError = true
            }

            if (!fieldsError)
                createPresenter.addData(firstName, lastName, dateOfBirth, email)
            if (fieldsError)
                setupFieldsErrorDialog(errorMessage).show()
        }
    }

    private fun setupFieldsErrorDialog(errorMessage: String): AlertDialog.Builder {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(errorMessage).setPositiveButton(R.string.create_screen_fields_error_message_ok, null)

        return builder
    }

    /**
     * Public methods
     */
    override fun getDatabaseHelper(): DatabaseHelper {
        return databaseHelper
    }

    override fun insertDataSuccess() {
        Toast.makeText(this.applicationContext, "Data inserted successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun insertDataError() {
        Toast.makeText(this.applicationContext, "There was an error!", Toast.LENGTH_SHORT).show()
    }
}