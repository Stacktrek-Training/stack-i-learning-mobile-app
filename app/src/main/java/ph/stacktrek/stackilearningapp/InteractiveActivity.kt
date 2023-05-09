package ph.stacktrek.stackilearningapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import meow.bottomnavigation.MeowBottomNavigation
import ph.stacktrek.stackilearningapp.dao.GuessThePictureDAO
import ph.stacktrek.stackilearningapp.databinding.ActivityInteractiveBinding
import ph.stacktrek.stackilearningapp.databinding.ActivityLoginBinding
import ph.stacktrek.stackilearningapp.fragment.homeFragment
import ph.stacktrek.stackilearningapp.fragment.profileFragment
import ph.stacktrek.stackilearningapp.interactive.DragAndDropActivity
import ph.stacktrek.stackilearningapp.interactive.GuessThePicture
import ph.stacktrek.stackilearningapp.interactive.HangmanActivity
import ph.stacktrek.stackilearningapp.interactive.MultipleChoiceActivity
import ph.stacktrek.stackilearningapp.interactive.PlaygroundActivity
import ph.stacktrek.stackilearningapp.profile.ProfileActivity


class InteractiveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInteractiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interactive)

        binding = ActivityInteractiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Image Loader
        //val fullstackImage = findViewById<ImageView>(R.id.iv_fullstack)
/*
       val multipleImage = findViewById<ImageView>(R.id.iv_multiplechoices)
        val dragdropImage = findViewById<ImageView>(R.id.iv_dragdrop)
        val guessImage = findViewById<ImageView>(R.id.iv_guess)
        val hangmanImage = findViewById<ImageView>(R.id.iv_hangman)
        val options = BitmapFactory.Options()
        .inSampleSize = 4 // Scale down the image by a factor of 4

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
       */

        addFragment(homeFragment.newInstance())
        binding.bottomNavigation.show(0)
        binding.bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_home))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_profie))

        binding.bottomNavigation.setOnClickMenuListener{
            when(it.id){
              0 -> {
                  replaceFragment(homeFragment.newInstance())
              }
                1 -> {
                    replaceFragment(profileFragment.newInstance())

                }
                else -> {
                    replaceFragment(homeFragment.newInstance())
                }

            }
        }
    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
}