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

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val score = intent.getIntExtra("score", 0)
        val question = intent.getIntExtra("question", 0)
        val success = intent.getStringExtra("success")

        if (success == "success") {
            binding.tvScore.text = "You guessed the word!"
        } else {
            binding.tvScore.text = "Your Score is $score out of $question."
        }

        binding.btnFinish.setOnClickListener {
            startActivity(Intent(this@ResultActivity, InteractiveActivity::class.java))
            finish()
        }
    }
}