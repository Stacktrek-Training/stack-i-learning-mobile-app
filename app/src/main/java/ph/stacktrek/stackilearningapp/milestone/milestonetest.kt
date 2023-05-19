package ph.stacktrek.stackilearningapp.milestone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.databinding.ActivityTestclickBinding

// Define the Milestone model
data class Milestone(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean = false,
    val dependencies: List<Int> = emptyList()
)

// MilestoneManager class to handle milestone management
class MilestoneManager {
    private val milestones: List<Milestone> = listOf(
        Milestone(1, "HTML", "Description of milestone 1"),
        Milestone(2, "GITHUB", "Description of milestone 2", dependencies = listOf(1)),
        Milestone(3, "CSS", "Description of milestone 3", dependencies = listOf(1, 2)),
        Milestone(4, "JAVASCRIPT", "Description of milestone 4", dependencies = listOf(1, 2,3)),
        Milestone(5, "Milestone 5", "Description of milestone 5", dependencies = listOf(1, 2,3,4)),
        Milestone(6, "Milestone 6", "Description of milestone 6", dependencies = listOf(1, 2,3,5)),
    )

    fun getMilestones(): List<Milestone> {
        return milestones
    }

    fun isMilestoneCompleted(milestoneId: Int): Boolean {
        val milestone = milestones.find { it.id == milestoneId }
        return milestone?.isCompleted ?: false
    }

    fun completeMilestone(milestoneId: Int) {
        val milestone = milestones.find { it.id == milestoneId }
        milestone?.isCompleted = true
    }

    fun areDependenciesCompleted(milestoneId: Int): Boolean {
        val milestone = milestones.find { it.id == milestoneId }
        return milestone?.dependencies?.all { isMilestoneCompleted(it) } ?: true
    }


    class milestonetest : AppCompatActivity() {
        private val milestoneManager = MilestoneManager()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_milestonetest)

            // Retrieve milestones from the MilestoneManager
            val milestones = milestoneManager.getMilestones()

            // Example: Create milestone views dynamically
            val containerLayout = findViewById<LinearLayout>(R.id.containerLayout)

            for (milestone in milestones) {
                val milestoneView =
                    layoutInflater.inflate(R.layout.milestone_view, containerLayout, false)
                val tvMilestoneTitle = milestoneView.findViewById<TextView>(R.id.tvMilestoneTitle)
                val tvMilestoneDescription =
                    milestoneView.findViewById<TextView>(R.id.tvMilestoneDescription)
                val btnMilestone = milestoneView.findViewById<Button>(R.id.btnMilestone)
                val milestoneLockIcon = milestoneView.findViewById<ImageView>(R.id.milestoneLockIcon)

                tvMilestoneTitle.text = milestone.title
                tvMilestoneDescription.text = milestone.description

                if (milestone.isCompleted) {
                    milestoneView.setBackgroundColor(Color.WHITE)
                    milestoneLockIcon.visibility = View.GONE
                    btnMilestone.isEnabled = false
                } else {
                    milestoneView.setBackgroundColor(Color.GRAY)
                    milestoneLockIcon.visibility = View.VISIBLE
                    btnMilestone.isEnabled = true
                }

                btnMilestone.setOnClickListener {
                    if (milestoneManager.areDependenciesCompleted(milestone.id)) {
                        milestoneManager.completeMilestone(milestone.id)
                        milestoneView.setBackgroundColor(Color.WHITE)
                        milestoneLockIcon.visibility = View.GONE
                        btnMilestone.isEnabled = false

                        val intent = Intent(this, testclick::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "Complete previous milestones first.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                containerLayout.addView(milestoneView)
            }
        }
    }
}