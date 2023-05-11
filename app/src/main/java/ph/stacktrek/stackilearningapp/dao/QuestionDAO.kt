package ph.stacktrek.stackilearningapp.dao

import android.provider.BaseColumns

class QuestionDAO private constructor() {

    class QuestionsTable : BaseColumns {
        companion object {
            const val TABLE_NAME = "questions"
            const val COLUMN_ID = "id"
            const val COLUMN_QUESTION = "question"
            const val COLUMN_OPTION1 = "option1"
            const val COLUMN_OPTION2 = "option2"
            const val COLUMN_OPTION3 = "option3"
            const val COLUMN_OPTION4 = "option4"
            const val COLUMN_ANSWERQ = "answerQ"
            const val COLUMN_IMAGE_PATH = "image_path"
        }
    }
}
