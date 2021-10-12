package com.example.telefonskiimenik.screens.update_delete

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import com.example.telefonskiimenik.R
import com.example.telefonskiimenik.base.BaseActivity
import com.example.telefonskiimenik.models.Person
import com.example.telefonskiimenik.screens.main.MainActivity
import com.example.telefonskiimenik.services.DatabaseHelper
import kotlinx.android.synthetic.main.activity_update_delete.*
import org.koin.android.ext.android.inject

class UpdateDeleteActivity: BaseActivity(), UpdateDeleteContract.View {

    private val updateDeletePresenter: UpdateDeletePresenter by inject()
    private lateinit var databaseHelper: DatabaseHelper

    /**
     * Lifecycle methods
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)
        setScreenTitle(getString(R.string.update_delete_screen_title))
        databaseHelper = DatabaseHelper(this)
        updateDeletePresenter.registerActivity(this)

        val person = intent.getParcelableExtra<Person>(MainActivity.update_delete_person)

        if (person != null)
            setupView(person)
    }

    /**
     * Private methods
     */
    private fun setupView(person: Person) {
        edit_text_first_name_ud.setText(person.firstName)
        edit_text_last_name_ud.setText(person.lastName)
        edit_text_date_of_birth_ud.setText(person.dateOfBirth)
        edit_text_email_ud.setText(person.email)

        val updateDialog = setupUpdateDialog(person)
        val deleteDialog = setupDeleteDialog(person)

        button_update_ud.setOnClickListener {
            updateDialog.show()
        }

        button_delete_ud.setOnClickListener {
            deleteDialog.show()
        }
    }

    private fun setupUpdateDialog(person: Person): AlertDialog.Builder {
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    //Yes button clicked
                    val firstName = edit_text_first_name_ud.text.toString()
                    val lastName = edit_text_last_name_ud.text.toString()
                    val dateOfBirth = edit_text_date_of_birth_ud.text.toString()
                    val email = edit_text_email_ud.text.toString()

                    updateDeletePresenter.updateData(person.id, firstName, lastName, dateOfBirth, email)
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    //No button clicked
                }
            }
        }

        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.update_delete_update_dialog_message)
            .setPositiveButton(R.string.update_delete_dialog_yes, dialogClickListener)
            .setNegativeButton(R.string.update_delete_dialog_no, dialogClickListener)

        return builder
    }

    private fun setupDeleteDialog(person: Person): AlertDialog.Builder {
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    //Yes button clicked
                    updateDeletePresenter.deleteData(person.id)
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    //No button clicked
                }
            }
        }

        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.update_delete_delete_dialog_message)
            .setPositiveButton(R.string.update_delete_dialog_yes, dialogClickListener)
            .setNegativeButton(R.string.update_delete_dialog_no, dialogClickListener)

        return builder
    }

    /**
     * Public methods
     */
    override fun getDatabaseHelper(): DatabaseHelper {
        return databaseHelper
    }

    override fun onUpdateSuccess() {
        Toast.makeText(this.applicationContext, R.string.update_delete_update_success_message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onUpdateError() {
        Toast.makeText(this.applicationContext, R.string.update_delete_update_error_message, Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteSuccess() {
        Toast.makeText(this.applicationContext, R.string.update_delete_delete_success_message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDeleteError() {
        Toast.makeText(this.applicationContext, R.string.update_delete_delete_error_message, Toast.LENGTH_SHORT).show()
    }
}