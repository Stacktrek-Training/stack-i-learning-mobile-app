package ph.stacktrek.stackilearningapp.interactive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ph.stacktrek.stackilearningapp.InteractiveActivity
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.dao.QuestionDAO
import ph.stacktrek.stackilearningapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO (STEP 6: Hide the status bar and get the details from intent and set it to the UI. And also add a click event to the finish button.)
        // START
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val score = intent.getIntExtra("score", 0)
        val question = intent.getIntExtra("question", 0)

        binding.tvScore.text = "Your Score is $score out of $question."

        binding.btnFinish.setOnClickListener {
            startActivity(Intent(this@ResultActivity, InteractiveActivity::class.java))
            finish()
        }
        // END
    }
}