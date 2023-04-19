package ph.stacktrek.stackilearningapp.interactive

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.model.MultipleChoicesData

class MultipleChoiceActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var totalQuestionsTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var ansA: Button
    private lateinit var ansB: Button
    private lateinit var ansC: Button
    private lateinit var ansD: Button
    private lateinit var submitBtn: Button

    private var score = 0
    private val totalQuestion = MultipleChoicesData.questions.size
    private var currentQuestionIndex = 0
    private var selectedAnswer = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_choice)

        totalQuestionsTextView = findViewById(R.id.total_question)
        questionTextView = findViewById(R.id.question)
        ansA = findViewById(R.id.ans_A)
        ansB = findViewById(R.id.ans_B)
        ansC = findViewById(R.id.ans_C)
        ansD = findViewById(R.id.ans_D)
        submitBtn = findViewById(R.id.submit_btn)

        ansA.setOnClickListener(this)
        ansB.setOnClickListener(this)
        ansC.setOnClickListener(this)
        ansD.setOnClickListener(this)
        submitBtn.setOnClickListener(this)

        totalQuestionsTextView.text = "Total questions : $totalQuestion"

        loadNewQuestion()
    }

    override fun onClick(view: View) {
        ansA.setBackgroundColor(Color.WHITE)
        ansB.setBackgroundColor(Color.WHITE)
        ansC.setBackgroundColor(Color.WHITE)
        ansD.setBackgroundColor(Color.WHITE)

        val clickedButton = view as Button
        if (clickedButton.id == R.id.submit_btn) {
            if (selectedAnswer == MultipleChoicesData.correctAnswers[currentQuestionIndex]) {
                score++
            }
            currentQuestionIndex++
            loadNewQuestion()
        } else {
            //choices button clicked
            selectedAnswer = clickedButton.text.toString()
            clickedButton.setBackgroundColor(Color.LTGRAY)
        }
    }

    private fun loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz()
            return
        }

        questionTextView.text = MultipleChoicesData.questions[currentQuestionIndex]
        ansA.text = MultipleChoicesData.choices[currentQuestionIndex][0]
        ansB.text = MultipleChoicesData.choices[currentQuestionIndex][1]
        ansC.text = MultipleChoicesData.choices[currentQuestionIndex][2]
        ansD.text = MultipleChoicesData.choices[currentQuestionIndex][3]
    }

    private fun finishQuiz() {
        val passStatus = if (score > totalQuestion * 0.60) "Passed" else "Failed"

        AlertDialog.Builder(this)
            .setTitle(passStatus)
            .setMessage("Score is $score out of $totalQuestion")
            .setPositiveButton("Restart") { _, _ -> restartQuiz() }
            .setCancelable(false)
            .show()
    }

    private fun restartQuiz() {
        score = 0
        currentQuestionIndex = 0
        loadNewQuestion()
    }
}
