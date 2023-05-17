package ph.stacktrek.stackilearningapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ph.stacktrek.stackilearningapp.databinding.ActivityInteractiveBinding
import ph.stacktrek.stackilearningapp.databinding.ActivityLearningTypeBinding
import ph.stacktrek.stackilearningapp.interactive.MultipleChoiceActivity
import ph.stacktrek.stackilearningapp.interactive.PlaygroundActivity

class LearningTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLearningTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_type)

        binding = ActivityLearningTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fullstackImage = findViewById<ImageView>(R.id.iv_fullstack)
        val minigameImage = findViewById<ImageView>(R.id.iv_mini_game)

        val options = BitmapFactory.Options()
        options.inSampleSize = 4 // Scale down the image by a factor of 4

        Picasso.get()
            .load(R.drawable.path_fullstack)
            .config(Bitmap.Config.RGB_565) // Use 16-bit color depth to reduce memory usage
            .resize(500, 500) // Resize the image to a smaller size
            .centerCrop() // Crop the image to fill the ImageView
            .into(fullstackImage)

        Picasso.get()
            .load(R.drawable.path_mini_game)
            .config(Bitmap.Config.RGB_565) // Use 16-bit color depth to reduce memory usage
            .resize(500, 500) // Resize the image to a smaller size
            .centerCrop() // Crop the image to fill the ImageView
            .into(minigameImage)


        binding.cvFullstack.setOnClickListener {

            val goFullstack = Intent(applicationContext, PlaygroundActivity::class.java)
            startActivity(goFullstack)

        }

        binding.cvMiniGame.setOnClickListener {
            val goMiniGame = Intent(applicationContext, MainActivity::class.java)
            startActivity(goMiniGame)
        }
    }
}