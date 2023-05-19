package ph.stacktrek.stackilearningapp.interactive

import android.content.Intent
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
    private val words = arrayOf("javascript", "python", "java", "kotlin", "ruby")


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

        resetGame()
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
        word = words.random().toUpperCase(Locale.ROOT)
        val shuffledWord = word!!.toCharArray().apply { shuffle() }.joinToString("")

        val sb = StringBuilder()
        for (i in 0 until shuffledWord.length) {
            sb.append("_ ")
        }
        textViewWord!!.text = sb.toString().trim()

        hangmanView.setRemainingMoves(remainingMoves)
        binding.textViewMoves.text = "Remaining moves: $remainingMoves"

        val keyboardView = binding.keyboardView
        for (i in 0 until keyboardView.childCount) {
            val letterView = keyboardView.getChildAt(i) as TextView
            letterView.isEnabled = true
        }
    }


    private fun updateWordView() {
        val sb = StringBuilder()
        var wordGuessed = true // Track if the word has been completely guessed

        for (i in 0 until word!!.length) {
            val letter = word!![i].toString()
            if (lettersGuessed[letter[0].toInt() - 'A'.toInt()]) {
                sb.append(letter)
            } else {
                sb.append("_ ")
                wordGuessed = false
            }
        }

        textViewWord!!.text = sb.toString().trim()

        if (wordGuessed) {
            val success = "success"

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("success", success)
            startActivity(intent)
        }
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
