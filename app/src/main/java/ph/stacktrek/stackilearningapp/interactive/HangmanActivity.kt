package ph.stacktrek.stackilearningapp.interactive

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ph.stacktrek.stackilearningapp.databinding.ActivityHangmanBinding
import java.util.Locale

class HangmanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHangmanBinding

    private var textViewWord: TextView? = null
    private var buttonReset: Button? = null
    private var remainingMoves = 7
    private val lettersGuessed = BooleanArray(26)
    private var word: String? = null

    // Declare a variable for the HangmanView
    private lateinit var hangmanView: HangmanView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHangmanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hangmanView = binding.hangmanView

        binding.textViewMoves.text = "Remaining moves: $remainingMoves"

        textViewWord = binding.textViewWord
        buttonReset = binding.buttonReset

        // Choose a random word
        word = "javascript".toUpperCase(Locale.ROOT)

        // Set the text of textViewWord to a series of dashes
        val sb = StringBuilder()
        for (i in 0 until word!!.length) {
            sb.append("_ ")
        }
        textViewWord!!.text = sb.toString().trim()

        // Set up the keyboard
        val keyboardView = binding.keyboardView
        keyboardView.setOnLetterClickListener { letter ->
            onLetterClick(letter)
        }

        buttonReset!!.setOnClickListener {
            resetGame()
        }
    }

    private fun resetGame() {
        remainingMoves = 7
        lettersGuessed.fill(false)
        word = "javascript".toUpperCase(Locale.ROOT)

        // Set the text of textViewWord to a series of dashes
        val sb = StringBuilder()
        for (i in 0 until word!!.length) {
            sb.append("_ ")
        }
        textViewWord!!.text = sb.toString().trim()

        hangmanView.setRemainingMoves(remainingMoves)
        binding.textViewMoves.text = "Remaining moves: $remainingMoves"

        // Re-enable all the letter views
        val keyboardView = binding.keyboardView
        for (i in 0 until keyboardView.getChildCount()) {
            val letterView = keyboardView.getChildAt(i) as TextView
            letterView.isEnabled = true
        }
    }

    private fun updateWordView() {
        val sb = StringBuilder()
        for (i in 0 until word!!.length) {
            val letter = word!![i].toString()
            sb.append(if (lettersGuessed[letter[0].toInt() - 'A'.toInt()]) letter else "_ ")
        }
        textViewWord!!.text = sb.toString().trim()
    }

    private fun onLetterClick(letter: String) {
        // Check if the game is already over
        if (remainingMoves == 0) {
            return
        }

        val index = letter[0].toInt() - 'A'.toInt()
        if (lettersGuessed[index]) {
            return
        }

        lettersGuessed[index] = true

        if (!word!!.contains(letter)) {
            remainingMoves--
            hangmanView.setRemainingMoves(remainingMoves)

            if (remainingMoves == 0) {
                Toast.makeText(this, "Game over! You ran out of letters.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.textViewMoves.text = "Remaining moves: $remainingMoves"

        updateWordView()

        val keyboardView = binding.keyboardView
        keyboardView.getChildAt(index).isEnabled = false
    }
}
