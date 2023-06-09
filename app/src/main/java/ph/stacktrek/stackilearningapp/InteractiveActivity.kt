package ph.stacktrek.stackilearningapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ph.stacktrek.stackilearningapp.databinding.ActivityInteractiveBinding
import ph.stacktrek.stackilearningapp.interactive.DragAndDropActivity
import ph.stacktrek.stackilearningapp.interactive.GuessGameActivity
import ph.stacktrek.stackilearningapp.interactive.HangmanActivity
import ph.stacktrek.stackilearningapp.interactive.MatchingGameActivity
import ph.stacktrek.stackilearningapp.interactive.MultipleChoiceActivity
import ph.stacktrek.stackilearningapp.interactive.PlaygroundActivity


class InteractiveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInteractiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interactive)

        binding = ActivityInteractiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Image Loader
        //val fullstackImage = findViewById<ImageView>(R.id.iv_fullstack)

        val multipleImage = findViewById<ImageView>(R.id.iv_multiplechoices)
        val dragdropImage = findViewById<ImageView>(R.id.iv_dragdrop)
        val guessImage = findViewById<ImageView>(R.id.iv_guess)
        val hangmanImage = findViewById<ImageView>(R.id.iv_hangman)
        val matchImage = findViewById<ImageView>(R.id.iv_match)

        val options = BitmapFactory.Options()
        options.inSampleSize = 4 // Scale down the image by a factor of 4

        // Load image with Picasso
        /*
        Picasso.get()
            .load(R.drawable.header_fullstack)
            .config(Bitmap.Config.RGB_565) // Use 16-bit color depth to reduce memory usage
            .resize(500, 500) // Resize the image to a smaller size
            .centerCrop() // Crop the image to fill the ImageView
            .into(fullstackImage)
         */
        Picasso.get()
            .load(R.drawable.category_multiplechoices)
            .config(Bitmap.Config.RGB_565) // Use 16-bit color depth to reduce memory usage
            .resize(500, 500) // Resize the image to a smaller size
            .centerCrop() // Crop the image to fill the ImageView
            .into(multipleImage)

        Picasso.get()
            .load(R.drawable.category_dragdrop)
            .config(Bitmap.Config.RGB_565) // Use 16-bit color depth to reduce memory usage
            .resize(500, 500) // Resize the image to a smaller size
            .centerCrop() // Crop the image to fill the ImageView
            .into(dragdropImage)

        Picasso.get()
            .load(R.drawable.category_guess)
            .config(Bitmap.Config.RGB_565) // Use 16-bit color depth to reduce memory usage
            .resize(500, 500) // Resize the image to a smaller size
            .centerCrop() // Crop the image to fill the ImageView
            .into(guessImage)

        Picasso.get()
            .load(R.drawable.category_hangman)
            .config(Bitmap.Config.RGB_565) // Use 16-bit color depth to reduce memory usage
            .resize(500, 500) // Resize the image to a smaller size
            .centerCrop() // Crop the image to fill the ImageView
            .into(hangmanImage)

        Picasso.get()
            .load(R.drawable.category_match)
            .config(Bitmap.Config.RGB_565) // Use 16-bit color depth to reduce memory usage
            .resize(500, 500) // Resize the image to a smaller size
            .centerCrop() // Crop the image to fill the ImageView
            .into(matchImage)


        binding.btnGo.setOnClickListener {

            val goPlayground = Intent(applicationContext, PlaygroundActivity::class.java)
            startActivity(goPlayground)

        }

        binding.cvMultipleChoices.setOnClickListener {
            val goMultipleChoices = Intent(applicationContext, MultipleChoiceActivity::class.java)
            startActivity(goMultipleChoices)
        }

        binding.cvDragAndDrop.setOnClickListener {
            val goDragAndDrop = Intent(applicationContext, DragAndDropActivity::class.java)
            startActivity(goDragAndDrop)
        }

        binding.cvGuessThePicture.setOnClickListener {
            val goGuess = Intent(applicationContext, GuessGameActivity::class.java)
            startActivity(goGuess)
        }

        binding.cvHangman.setOnClickListener {
            val goHangman = Intent(applicationContext, HangmanActivity::class.java)
            startActivity(goHangman)
        }

        binding.cvMatch.setOnClickListener {
            val goMatch = Intent(applicationContext, MatchingGameActivity::class.java)
            startActivity(goMatch)
        }

    }
}