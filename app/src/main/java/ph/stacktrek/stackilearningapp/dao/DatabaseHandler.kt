package ph.stacktrek.stackilearningapp.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context):
    SQLiteOpenHelper(context, DATABASENAME,null, DATABASEVERSION) {

    companion object {
        private val DATABASEVERSION = 1
        private val DATABASENAME = "StackLearningDatabase"

        //User
        const val TABLE_USER = "user_table"
        const val TABLE_USER_ID = "id"
        const val TABLE_USER_EMAIL = "email"
        const val TABLE_USER_USERNAME = "username"
        const val TABLE_USER_PASSWORD = "password"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USERS_TABLE =
            "CREATE TABLE $TABLE_USER " +
                    "($TABLE_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$TABLE_USER_EMAIL TEXT UNIQUE, " +
                    "$TABLE_USER_USERNAME TEXT UNIQUE, " +
                    "$TABLE_USER_PASSWORD TEXT)"

        db?.execSQL(CREATE_USERS_TABLE)

        db?.execSQL("Insert into $TABLE_USER ($TABLE_USER_EMAIL, $TABLE_USER_USERNAME, $TABLE_USER_PASSWORD) values ('rickyjundechino@gmail.com', 'admin', 'admin')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }
}