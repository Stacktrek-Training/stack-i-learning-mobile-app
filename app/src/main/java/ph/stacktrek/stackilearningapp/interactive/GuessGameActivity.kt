package ph.stacktrek.stackilearningapp.interactive

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.dao.DatabaseHandler
import ph.stacktrek.stackilearningapp.dao.GuessThePictureDAO
import ph.stacktrek.stackilearningapp.databinding.ActivityGuessGameBinding
import ph.stacktrek.stackilearningapp.model.GuessThePictureData
import ph.stacktrek.stackilearningapp.model.QuestionModel

class GuessGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuessGameBinding
    private var textViewQuestion: TextView? = null
    private var textViewScore: TextView? = null
    private var textViewQuestionCount: TextView? = null
    private var textViewCountDown: TextView? = null
    private var imagePath: ImageView? = null
    private var rbGroup: RadioGroup? = null
    private var rb1: RadioButton? = null
    private var rb2: RadioButton? = null
    private var rb3: RadioButton? = null
    private var rb4: RadioButton? = null
    private var buttonConfirmNext: Button? = null

    private var textColorDefaultRb: ColorStateList = ColorStateList.valueOf(0)

    private var questionList: List<QuestionModel> = ArrayList()
    private var questionCounter = 0
    private var questionCountTotal = 0
    private var currentQuestion: QuestionModel? = null
    private var countDownTimer: CountDownTimer? = null

    private var score = 0
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_guess_game)

        binding = ActivityGuessGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // TODO (STEP 4: Get the NAME from intent and assign it the variable.)
        // START
        // END

        textViewQuestion = findViewById(R.id.tv_question)
        textViewScore = findViewById(R.id.tv_score)
        textViewQuestionCount = findViewById(R.id.tv_question_count)
        textViewCountDown = findViewById(R.id.tv_timer)
        imagePath = findViewById(R.id.iv_image)
        rbGroup = findViewById(R.id.radio_group)
        rb1 = findViewById(R.id.rb1)
        rb2 = findViewById(R.id.rb2)
        rb3 = findViewById(R.id.rb3)
        rb4 = findViewById(R.id.rb4)
        buttonConfirmNext = findViewById(R.id.btn_submit)

        textColorDefaultRb = rb1?.textColors ?: ColorStateList.valueOf(0)

        val dbHelper = DatabaseHandler(this)
        questionList = dbHelper.getAllQuestions()
        questionCountTotal = questionList.size
        questionList = questionList.shuffled()
        textViewScore?.text = "Score: $score"

        showNextQuestion()

        buttonConfirmNext?.setOnClickListener {
            if (!answered) {
                if (rb1?.isChecked == true || rb2?.isChecked == true || rb3?.isChecked == true || rb4?.isChecked == true) {
                    checkAnswer()
                } else {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                }
            } else {
                showNextQuestion()
            }
        }
    }

    private fun showNextQuestion() {
        rb1?.setTextColor(textColorDefaultRb)
        rb2?.setTextColor(textColorDefaultRb)
        rb3?.setTextColor(textColorDefaultRb)
        rb4?.setTextColor(textColorDefaultRb)
        rbGroup?.clearCheck()

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList[questionCounter]

            currentQuestion?.imagePath?.let {
                Picasso.get()
                    .load(it)
                    .resize(500, 500)
                    .onlyScaleDown()
                    .into(imagePath)
            }


            textViewQuestion?.text = currentQuestion?.question
            rb1?.text = currentQuestion?.option1
            rb2?.text = currentQuestion?.option2
            rb3?.text = currentQuestion?.option3
            rb4?.text = currentQuestion?.option4

            questionCounter++
            textViewQuestionCount?.text = "Question: $questionCounter/$questionCountTotal"
            answered = false
            buttonConfirmNext?.text = "Confirm"

            // Start the countdown timer
            countDownTimer?.cancel() // Cancel any previous timer
            countDownTimer = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val secondsLeft = millisUntilFinished / 1000
                    textViewCountDown?.text = "00:$secondsLeft"
                }

                override fun onFinish() {
                    // When the countdown timer finishes, check the answer
                    checkAnswer()
                }
            }.start()
        } else {
            finishQuiz()
        }
    }

    private fun checkAnswer() {
        answered = true

        val selectedRb = findViewById<RadioButton>(rbGroup!!.checkedRadioButtonId)
        val selectedAnswer = rbGroup?.indexOfChild(selectedRb)?.plus(1)

        if (selectedAnswer == currentQuestion?.answerQ) {
            score++
            textViewScore?.text = "Score: $score"
        }

        showSolution()
    }

    private fun showSolution() {
        rb1?.setTextColor(Color.RED)
        rb2?.setTextColor(Color.RED)
        rb3?.setTextColor(Color.RED)
        rb4?.setTextColor(Color.RED)

        when (currentQuestion?.answerQ) {
            1 -> rb1?.setTextColor(Color.GREEN)
            2 -> rb2?.setTextColor(Color.GREEN)
            3 -> rb3?.setTextColor(Color.GREEN)
            4 -> rb4?.setTextColor(Color.GREEN)
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext?.text = "Next"
        } else {
            buttonConfirmNext?.text = "Finish"
        }
    }

    private fun finishQuiz() {
        finish()
    }
}