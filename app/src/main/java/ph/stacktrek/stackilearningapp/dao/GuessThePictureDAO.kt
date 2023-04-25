package ph.stacktrek.stackilearningapp.dao

import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.model.GuessThePictureData

object GuessThePictureDAO {

    // TODO (STEP 1: Create a constant variables which we required in the result screen.)
    // START
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    // END

    fun getQuestions(): ArrayList<GuessThePictureData> {
        val questionsList = ArrayList<GuessThePictureData>()

        // 1
        val que1 = GuessThePictureData(
            1, "Guess the programming language.",
            R.drawable.guess_html,
            "CSS", "HTML",
            "SQL", "Python", 2
        )

        questionsList.add(que1)

        // 2
        val que2 = GuessThePictureData(
            2, "Guess the logo.",
            R.drawable.guess_apple,
            "Apple", "Microsoft",
            "Razer", "Acer", 1
        )

        questionsList.add(que2)

        // 3
        val que3 = GuessThePictureData(
            3, "What control structure is shown.",
            R.drawable.guess_loop,
            "Sequence", "Loops",
            "If-Else", "Array", 2
        )

        questionsList.add(que3)

        // 4
        val que4 = GuessThePictureData(
            4, "The shown snippet is ",
            R.drawable.guessql,
            "C", "Kotlin",
            "Javascript", "SQL", 4
        )

        questionsList.add(que4)

        // 5
        val que5 = GuessThePictureData(
            5, "Guess the logo",
            R.drawable.guess_microsoft,
            "Asus", "Dell",
            "Microsoft", "Linux", 3
        )

        questionsList.add(que5)

        // 6
        val que6 = GuessThePictureData(
            6, "What programming language is this?",
            R.drawable.guess_code,
            "Python", "Vue",
            "Java", "Ruby", 1
        )

        questionsList.add(que6)

        // 7
        val que7 = GuessThePictureData(
            7, "Guess the logo",
            R.drawable.guess_java,
            "Java", "Python",
            "C#", "Javascript", 1
        )

        questionsList.add(que7)

        // 8
        val que8 = GuessThePictureData(
            8, "Guess the logo",
            R.drawable.guess_node,
            "React", "Go",
            "Node JS", "Kotlin", 3
        )

        questionsList.add(que8)

        // 9
        val que9 = GuessThePictureData(
            9, "Guess the logo",
            R.drawable.guess_python,
            "Perl", "Fortran",
            "Python", "Lisp", 3
        )

        questionsList.add(que9)

        // 10
        val que10 = GuessThePictureData(
            10, "Guess the logo",
            R.drawable.guess_react,
            "R", "Typescript",
            "Dart", "React", 4
        )

        questionsList.add(que10)

        return questionsList
    }
}