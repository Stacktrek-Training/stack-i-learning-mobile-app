package ph.stacktrek.stackilearningapp.interactive

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.model.DragAndDropData

class DragAndDropActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private lateinit var currentQuestion: DragAndDropData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop)

        // Get references to the views
        val questionTextView = findViewById<TextView>(R.id.question_textview)
        val questionFilledTextView = findViewById<TextView>(R.id.question_filled_textview)
        val choice1TextView = findViewById<TextView>(R.id.choice1_textview)
        val choice2TextView = findViewById<TextView>(R.id.choice2_textview)
        val choice3TextView = findViewById<TextView>(R.id.choice3_textview)
        val choice4TextView = findViewById<TextView>(R.id.choice4_textview)
        val answerEditText = findViewById<EditText>(R.id.answer_edittext)
        val checkButton = findViewById<Button>(R.id.check_button)

        // Set drag listeners on the answer EditText and the answer choices TextViews
        answerEditText.setOnDragListener(object : View.OnDragListener {
            override fun onDrag(v: View, event: DragEvent): Boolean {
                when (event.action) {
                    DragEvent.ACTION_DRAG_STARTED -> {
                        return true
                    }
                    DragEvent.ACTION_DRAG_ENTERED -> {
                        return true
                    }
                    DragEvent.ACTION_DRAG_LOCATION -> {
                        return true
                    }
                    DragEvent.ACTION_DRAG_EXITED -> {
                        return true
                    }
                    DragEvent.ACTION_DROP -> {
                        val item = event.clipData.getItemAt(0)
                        val dragData = item.text.toString()

                        // Set the text of the EditText to the dragged item's text
                        (v as EditText).setText(dragData)

                        // Clear the focus from the EditText to hide the keyboard
                        v.clearFocus()

                        // Set the text of the questionFilledTextView to the question with the blank space filled in
                        val questionFilled = currentQuestion.snippet.replace("_____", dragData)
                        questionFilledTextView.text = questionFilled

                        return true
                    }
                    DragEvent.ACTION_DRAG_ENDED -> {
                        return true
                    }
                    else -> return false
                }
            }
        })

        choice1TextView.setOnLongClickListener { view ->
            val dragShadow = View.DragShadowBuilder(view)
            val clipData = ClipData.newPlainText("", (view as TextView).text)
            view.startDragAndDrop(clipData, dragShadow, view, 0)
        }

        choice2TextView.setOnLongClickListener { view ->
            val dragShadow = View.DragShadowBuilder(view)
            val clipData = ClipData.newPlainText("", (view as TextView).text)
            view.startDragAndDrop(clipData, dragShadow, view, 0)
        }

        choice3TextView.setOnLongClickListener { view ->
            val dragShadow = View.DragShadowBuilder(view)
            val clipData = ClipData.newPlainText("", (view as TextView).text)
            view.startDragAndDrop(clipData, dragShadow, view, 0)
        }

        choice4TextView.setOnLongClickListener { view ->
            val dragShadow = View.DragShadowBuilder(view)
            val clipData = ClipData.newPlainText("", (view as TextView).text)
            view.startDragAndDrop(clipData, dragShadow, view, 0)
        }

        // Set a click listener on the "Check Answer" button
        checkButton.setOnClickListener {
            // Get the text of the EditText
            val answer = answerEditText.text.toString()

            // Check if the answer is correct
            val isAnswerCorrect = checkAnswer(answer)

            // Show a toast message to the user based on whether the answer is correct or not
            if (isAnswerCorrect) {
                Toast.makeText(this@DragAndDropActivity, "Correct answer!", Toast.LENGTH_SHORT)
                    .show()

                // Increment the currentQuestionIndex variable
                currentQuestionIndex++

                // Check if there are more questions
                if (currentQuestionIndex < DragAndDropData.questionsList.size) {
                    // Set the text of the views to the next question and choices
                    currentQuestion = DragAndDropData.questionsList[currentQuestionIndex]
                    questionTextView.text = currentQuestion.question
                    questionFilledTextView.text = currentQuestion.snippet.replace("_____", "_____ ")
                    choice1TextView.text = currentQuestion.choices[0]
                    choice2TextView.text = currentQuestion.choices[1]
                    choice3TextView.text = currentQuestion.choices[2]
                    choice4TextView.text = currentQuestion.choices[3]

                    // Clear the text of the answer EditText
                    answerEditText.setText("")
                } else {
                    // There are no more questions, show a message to the user

                    AlertDialog.Builder(this)
                        .setTitle("Quiz Done!")
                        .setMessage("You answered all the queries!")
                        .setPositiveButton("Restart") { _, _ ->
                            val intent = Intent(this, DragAndDropActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .setCancelable(false)
                        .show()
                }
            } else {
                Toast.makeText(
                    this@DragAndDropActivity,
                    "Wrong answer, try again!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Reset the questionFilledTextView to its initial state
            questionFilledTextView.text = currentQuestion.snippet.replace("_____", "_____ ")
        }

        // Set the current question and choices
        currentQuestion = DragAndDropData.questionsList[currentQuestionIndex]
        questionTextView.text = currentQuestion.question
        questionFilledTextView.text = currentQuestion.snippet.replace("_____", "_____ ")
        choice1TextView.text = currentQuestion.choices[0]
        choice2TextView.text = currentQuestion.choices[1]
        choice3TextView.text = currentQuestion.choices[2]
        choice4TextView.text = currentQuestion.choices[3]
    }

    /**
     * A helper function to check if the answer is correct or not.
     */
    private fun checkAnswer(answer: String): Boolean {
        return answer.equals(currentQuestion.answer, ignoreCase = true)
    }
}