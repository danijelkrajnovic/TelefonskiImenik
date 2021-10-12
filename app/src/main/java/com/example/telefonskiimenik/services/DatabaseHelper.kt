package com.example.telefonskiimenik.services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.telefonskiimenik.R

class DatabaseHelper(val context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "PhoneBookDatabase"
        private val TABLE_NAME = "PhoneBookTable"
        private val COLUMN_ID = "id"
        private val COLUMN_FIRST_NAME = "first_name"
        private val COLUMN_LAST_NAME = "last_name"
        private val COLUMN_DATE_OF_BIRTH = "date_of_birth"
        private val COLUMN_EMAIL = "email"
        val FIRST_NAME_LENGTH = 20
        val LAST_NAME_LENGTH = 20
        val DATE_OF_BIRTH_LENGTH = 20
        val EMAIL_LENGTH = 30
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " VARCHAR(" + FIRST_NAME_LENGTH +"), " + COLUMN_LAST_NAME + " VARCHAR(" + LAST_NAME_LENGTH + "), " +
                COLUMN_DATE_OF_BIRTH + " VARCHAR(" + DATE_OF_BIRTH_LENGTH + "), " + COLUMN_EMAIL + " VARCHAR(" + EMAIL_LENGTH + "))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val query = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(query)
        onCreate(db)
    }

    /**
     * Public methods
     */
    fun addData(firstName: String, lastName: String, dateOfBirth: String, email: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FIRST_NAME, firstName)
        contentValues.put(COLUMN_LAST_NAME, lastName)
        contentValues.put(COLUMN_DATE_OF_BIRTH, dateOfBirth)
        contentValues.put(COLUMN_EMAIL, email)
        val result = db.insert(TABLE_NAME, null, contentValues)

        if (result == -1L)
            return false
        return true
    }

    fun getData(columnName: String, value: String): Cursor {
        val db = this.writableDatabase
        val stringArray = context.resources.getStringArray(R.array.main_screen_search_by)

        var query = ""
        // first name
        if (columnName == stringArray[0] && value != "")
            query = "SELECT *  FROM $TABLE_NAME WHERE $COLUMN_FIRST_NAME = '$value'"
        // last name
        if (columnName == stringArray[1] && value != "")
            query = "SELECT *  FROM $TABLE_NAME WHERE $COLUMN_LAST_NAME = '$value'"
        // date of birth
        if (columnName == stringArray[2] && value != "")
            query = "SELECT *  FROM $TABLE_NAME WHERE $COLUMN_DATE_OF_BIRTH = '$value'"
        // email
        if (columnName == stringArray[3] && value != "")
            query = "SELECT *  FROM $TABLE_NAME WHERE $COLUMN_EMAIL = '$value '"
        // all
        if (value == "")
            query = "SELECT *  FROM $TABLE_NAME"

        return db.rawQuery(query, null)
    }

    fun updateData(id: String, firstName: String, lastName: String, dateOfBirth: String, email: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_FIRST_NAME, firstName)
        contentValues.put(COLUMN_LAST_NAME, lastName)
        contentValues.put(COLUMN_DATE_OF_BIRTH, dateOfBirth)
        contentValues.put(COLUMN_EMAIL, email)
        val whereClause = "$COLUMN_ID=?"
        val whereArgs = arrayOf(id)
        val result = db.update(TABLE_NAME, contentValues, whereClause, whereArgs)

        if (result == -1)
            return false
        return true
    }

    fun deleteData(id: String): Boolean {
        val db = this.writableDatabase
        val whereClause = "$COLUMN_ID=?"
        val whereArgs = arrayOf(id)
        val result = db.delete(TABLE_NAME, whereClause, whereArgs)

        if (result == -1)
            return false
        return true
    }

}