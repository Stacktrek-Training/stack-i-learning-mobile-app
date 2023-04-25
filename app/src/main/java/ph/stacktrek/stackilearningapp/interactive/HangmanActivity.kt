package ph.stacktrek.stackilearningapp.interactive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.stacktrek.stackilearningapp.R
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import ph.stacktrek.stackilearningapp.databinding.ActivityHangmanBinding
import ph.stacktrek.stackilearningapp.databinding.ActivityInteractiveBinding
import java.util.Locale

class HangmanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHangmanBinding

    private var textViewWord: TextView? = null
    private var imageViewHangman: ImageView? = null
    private var buttonReset: Button? = null
    private var remainingMoves = 7
    private val lettersGuessed = BooleanArray(26)
    private var word: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman)

        binding = ActivityHangmanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textViewWord = findViewById(R.id.text_view_word)
        imageViewHangman = findViewById(R.id.image_view_hangman)
        buttonReset = findViewById(R.id.button_reset)

        // Choose a random word
        word = "javascript".toUpperCase(Locale.ROOT)

        // Set the text of textViewWord to a series of dashes
        val sb = StringBuilder()
        for (i in 0 until word!!.length) {
            sb.append("_ ")
        }
        binding.textViewWord.text = sb.toString().trim() // use safe call operator

        binding.buttonReset.setOnClickListener {
            resetGame()
        }
    }

    private fun resetGame() {
        // Reset the game state
        remainingMoves = 7
        for (i in lettersGuessed.indices) {
            lettersGuessed[i] = false
        }

        // Reset the hangman image and visibility of the reset button
        binding.imageViewHangman.setImageResource(R.drawable.hangman0)
        binding.buttonReset.visibility = View.INVISIBLE

        // Recreate the activity
        recreate()
    }

    fun onLetterButtonClick(view: View) {

        if (remainingMoves == 0) {
            // User has used all remaining letters
            Toast.makeText(this, "Game over, you have used all remaining letters", Toast.LENGTH_SHORT).show()
            binding.buttonReset.visibility = View.VISIBLE
            return
        }

        val button = view as Button
        val letter = button.text[0]
        val index = letter.code - 'A'.code
        if (lettersGuessed[index]) {
            // Letter has already been guessed
            return
        }
        lettersGuessed[index] = true

        // Set the text color of the button to the clicked color
        button.setTextColor(ContextCompat.getColor(this, R.color.button_text_clicked))

        if (word!!.indexOf(letter) >= 0) {
            // Letter is in the word
            val sb = StringBuilder(binding.textViewWord.text)
            for (i in 0 until word!!.length) {
                if (word!![i] == letter) {
                    sb.setCharAt(i * 2, letter)
                }
            }
            binding.textViewWord.text = sb.toString()
            if (binding.textViewWord.text.indexOf('_') < 0) {
                // User has guessed all the letters
                Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show()
                binding.buttonReset.visibility = View.VISIBLE
            }
        } else {
            // Letter is not in the word
            remainingMoves--
            binding.textViewMoves.text = "Remaining moves: $remainingMoves"
            when (remainingMoves) {
                6 -> binding.imageViewHangman.setImageResource(R.drawable.hangman0)
                5 -> binding.imageViewHangman.setImageResource(R.drawable.hangman0)
                4 -> binding.imageViewHangman.setImageResource(R.drawable.hangman0)
                3 -> binding.imageViewHangman.setImageResource(R.drawable.hangman0)
                2 -> binding.imageViewHangman.setImageResource(R.drawable.hangman0)
                1 -> binding.imageViewHangman.setImageResource(R.drawable.hangman0)
                0 -> {

                    // User has made too many incorrect guesses
                    Toast.makeText(this, "You lose!", Toast.LENGTH_SHORT).show()
                    binding.textViewWord.text = word
                    binding.buttonReset.visibility = View.VISIBLE
                }
            }
        }
    }
}