package ph.stacktrek.stackilearningapp.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.dao.QuestionDAO.*
import ph.stacktrek.stackilearningapp.model.QuestionModel

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {

    companion object {
        private val DATABASEVERSION = 1
        private val DATABASENAME = "StackLearningDatabase.db"

        // User
        const val TABLE_USER = "user_table"
        const val COLUMN_USER_ID = "id"
        //const val COLUMN_USER_NAME = "fullname"
        //const val COLUMN_USER_ADDRESS = "email"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_PASSWORD = "password"

    }

    private var db: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase?) {
        //Table Creation for Users
        val CREATE_USERS_TABLE =
            "CREATE TABLE $TABLE_USER " +
                "($COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USER_EMAIL TEXT UNIQUE, " +
                "$COLUMN_USER_PASSWORD TEXT)"

        db?.execSQL(CREATE_USERS_TABLE)

        db?.execSQL("Insert into $TABLE_USER ($COLUMN_USER_EMAIL, $COLUMN_USER_PASSWORD) values ('test@gmail.com', 'admin')")

        this.db = db

        val SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWERQ + " INTEGER, " +
                QuestionsTable.COLUMN_IMAGE_PATH + " INTEGER" +
                ")"
        db?.execSQL(SQL_CREATE_QUESTIONS_TABLE)
        fillQuestionsTable()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME)
        onCreate(db)
    }

    private fun fillQuestionsTable() {
        val q1 = QuestionModel("Guess the programming language.", "CSS", "HTML", "SQL", "Python", 2, R.drawable.guess_html)
        addQuestion(q1)
        val q2 = QuestionModel("Guess the logo", "Apple", "Microsoft", "Razer", "Acer", 1, R.drawable.guess_apple)
        addQuestion(q2)
        val q3 = QuestionModel("What control structure is shown.", "Sequence", "Loops", "If-Else", "Array", 2, R.drawable.guess_loop)
        addQuestion(q3)
        val q4 = QuestionModel("The shown snippet is ", "C", "Kotlin", "JavaScript", "SQL", 4, R.drawable.guessql)
        addQuestion(q4)
        val q5 = QuestionModel("Guess the logo", "Asus", "Dell", "Microsoft", "Linux", 3, R.drawable.guess_microsoft)
        addQuestion(q5)
        val q6 = QuestionModel("What programming language is this?", "Python", "Vue", "Java", "Ruby", 1, R.drawable.guess_python)
        addQuestion(q6)
        val q7 = QuestionModel("Guess the logo.", "Java", "Python", "C#", "Javascript", 1, R.drawable.guess_java)
        addQuestion(q7)
        val q8 = QuestionModel("Guess the logo.", "React", "Go", "Node JS", "Kotlin", 3, R.drawable.guess_node)
        addQuestion(q8)
        val q9 = QuestionModel("Guess the logo.", "Perl", "Fortran", "Python", "Lisp", 3, R.drawable.guess_python)
        addQuestion(q9)
        val q10 = QuestionModel("Guess the logo.", "R", "Typescript", "Dart", "React", 4, R.drawable.guess_react)
        addQuestion(q10)
    }

    private fun addQuestion(question: QuestionModel) {
        val cv = ContentValues()
        cv.put(QuestionsTable.COLUMN_QUESTION, question.question)
        cv.put(QuestionsTable.COLUMN_OPTION1, question.option1)
        cv.put(QuestionsTable.COLUMN_OPTION2, question.option2)
        cv.put(QuestionsTable.COLUMN_OPTION3, question.option3)
        cv.put(QuestionsTable.COLUMN_OPTION4, question.option4)
        cv.put(QuestionsTable.COLUMN_ANSWERQ, question.answerQ)
        cv.put(QuestionsTable.COLUMN_IMAGE_PATH, question.imagePath)
        db?.insert(QuestionsTable.TABLE_NAME, null, cv)
    }

    @SuppressLint("Range")
    fun getAllQuestions(): List<QuestionModel> {
        val questionList = ArrayList<QuestionModel>()
        db = readableDatabase
        val c = db?.rawQuery("SELECT * FROM ${QuestionsTable.TABLE_NAME}", null)

        c?.let {
            if (it.moveToFirst()) {
                do {
                    val question = QuestionModel().apply {
                        question = it.getString(it.getColumnIndex(QuestionsTable.COLUMN_QUESTION))
                        option1 = it.getString(it.getColumnIndex(QuestionsTable.COLUMN_OPTION1))
                        option2 = it.getString(it.getColumnIndex(QuestionsTable.COLUMN_OPTION2))
                        option3 = it.getString(it.getColumnIndex(QuestionsTable.COLUMN_OPTION3))
                        option4 = it.getString(it.getColumnIndex(QuestionsTable.COLUMN_OPTION4))
                        answerQ = it.getInt(it.getColumnIndex(QuestionsTable.COLUMN_ANSWERQ))
                        imagePath = it.getInt(it.getColumnIndex(QuestionsTable.COLUMN_IMAGE_PATH))
                    }
                    questionList.add(question)
                } while (it.moveToNext())
            }
            it.close()
        }
        return questionList
    }
}
