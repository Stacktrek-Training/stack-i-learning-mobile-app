package ph.stacktrek.stackilearningapp.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class UserDAO(context: Context) {
    private val dbHandler: DatabaseHandler = DatabaseHandler(context)

    companion object {
        const val TABLE_USER = "user_table"
        const val TABLE_USER_ID = "id"
        const val TABLE_USER_EMAIL = "email"
        const val TABLE_USER_PASSWORD = "password"
    }

    fun addUser(email: String, password: String): Long {
        val db = dbHandler.writableDatabase

        val values = ContentValues().apply {
            put(TABLE_USER_EMAIL, email)
            put(TABLE_USER_PASSWORD, password)
        }

        return db.insert(TABLE_USER, null, values)
    }

    fun validateUser(email: String, password: String): Boolean {
        val db = dbHandler.readableDatabase
        val query = "SELECT * FROM $TABLE_USER WHERE $TABLE_USER_EMAIL=? AND $TABLE_USER_PASSWORD=?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(email, password))

        return if (cursor.count > 0) {
            cursor.close()
            true
        } else {
            cursor.close()
            false
        }
    }
}
