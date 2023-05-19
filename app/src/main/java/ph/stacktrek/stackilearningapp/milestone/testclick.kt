package ph.stacktrek.stackilearningapp.milestone
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.databinding.ActivityEditProfileBinding
import ph.stacktrek.stackilearningapp.databinding.ActivityMilestonetestBinding
import ph.stacktrek.stackilearningapp.profile.ProfileActivity

class testclick : AppCompatActivity() {

    private lateinit var binding: ActivityMilestonetestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testclick)

        val btnNext: Button = findViewById(R.id.btnnext)
        btnNext.setOnClickListener {
            // Handle button click event here
            // Return to the main activity
            val intent = Intent(this, MilestoneManager.milestonetest::class.java)
            startActivity(intent)
            finish() // Optional: Finish the current activity to prevent going back to it
        }

    }
}