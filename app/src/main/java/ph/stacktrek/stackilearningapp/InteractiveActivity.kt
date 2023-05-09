package ph.stacktrek.stackilearningapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ph.stacktrek.stackilearningapp.dao.GuessThePictureDAO
import ph.stacktrek.stackilearningapp.databinding.ActivityInteractiveBinding
import ph.stacktrek.stackilearningapp.databinding.ActivityLoginBinding
import ph.stacktrek.stackilearningapp.interactive.DragAndDropActivity
import ph.stacktrek.stackilearningapp.interactive.GuessThePicture
import ph.stacktrek.stackilearningapp.interactive.HangmanActivity
import ph.stacktrek.stackilearningapp.interactive.MultipleChoiceActivity
import ph.stacktrek.stackilearningapp.interactive.PlaygroundActivity


class InteractiveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInteractiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interactive)

        binding = ActivityInteractiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpTabBar()
        //Image Loader
        //val fullstackImage = findViewById<ImageView>(R.id.iv_fullstack)

        val multipleImage = findViewById<ImageView>(R.id.iv_multiplechoices)
        val dragdropImage = findViewById<ImageView>(R.id.iv_dragdrop)
        val guessImage = findViewById<ImageView>(R.id.iv_guess)
        val hangmanImage = findViewById<ImageView>(R.id.iv_hangman)

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
            val goGuess = Intent(applicationContext, GuessThePicture::class.java)
            startActivity(goGuess)
        }

        binding.cvHangman.setOnClickListener {
            val goHangman = Intent(applicationContext, HangmanActivity::class.java)
            startActivity(goHangman)
        }

    }
    private fun setUpTabBar(){
        binding.bottomNav.setOnItemSelectedListener {
            when(it){
                R.id.nav_home -> binding.itemTextView.text ="Home"
                R.id.nav_leaderboard -> binding.itemTextView.text ="Leader Board"
                R.id.nav_profile -> {
                    binding.itemTextView.text = "Profile"
                    binding.bottomNav.showBadge(R.id.nav_profileid)
                }
                R.id.nav_profileid -> {
                    binding.itemTextView.text = "Profile"
                    binding.bottomNav.dismissBadge(R.id.nav_profileid)
                }
                }
            }
        }
    }