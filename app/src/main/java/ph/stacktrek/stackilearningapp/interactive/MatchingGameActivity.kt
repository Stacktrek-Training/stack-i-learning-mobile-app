package ph.stacktrek.stackilearningapp.interactive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.adapters.CardAdapter
import ph.stacktrek.stackilearningapp.model.CardModel

class MatchingGameActivity : AppCompatActivity() {

    private lateinit var cards: List<CardModel>
    private lateinit var adapter: CardAdapter
    private var secondCard: CardModel? = null
    private var canClickCards: Boolean = true // Flag variable to track card clicking

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_game)

        val gridView = findViewById<GridView>(R.id.gridview)
        val startButton = findViewById<Button>(R.id.start_button)

        cards = createCards()
        adapter = CardAdapter(this, cards)

        gridView.adapter = adapter

        // Set click listener for start button
        startButton.setOnClickListener { startGame() }

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (canClickCards) {
                handleCardClick(cards[position])
            }
        }
    }

    // Helper method to create list of cards
    private fun createCards(): List<CardModel> {
        val cardImages = listOf(
            R.drawable.guess_apple,
            R.drawable.guess_java,
            R.drawable.guess_python,
            R.drawable.guess_react,
            R.drawable.guess_microsoft,
            R.drawable.guess_node,
            R.drawable.logo_ruby,
            R.drawable.logo_swift
        )

        val cards = mutableListOf<CardModel>()

        // Add two cards with each image to the list
        for (i in cardImages.indices) {
            val image = cardImages[i]
            cards.add(CardModel(i * 2, image))
            cards.add(CardModel(i * 2 + 1, image))
        }

        // Shuffle the list
        cards.shuffle()

        return cards
    }


    // Method called when start button is clicked
    private var firstCard: CardModel? = null

    private fun startGame() {
        // Set all cards to unmatched
        cards.forEach { it.isMatched = false }

        // Shuffle the cards
        cards = cards.shuffled()

        // Reset first card selection
        firstCard = null

        // Update adapter to show blank cards
        adapter.notifyDataSetChanged()
    }

    private fun handleCardClick(card: CardModel) {
        if (card.isMatched) {
            // Card is already matched, do nothing
            return
        }

        if (firstCard == null) {
            // First card selected
            firstCard = card
            card.isMatched = true
            adapter.notifyDataSetChanged()
        } else {
            // Second card selected, check for match
            secondCard = card
            card.isMatched = true
            adapter.notifyDataSetChanged()

            // Disable card clicking during the delay
            canClickCards = false

            // Wait for 2 seconds before checking for match
            Handler().postDelayed({
                if (secondCard!!.imageResourceId == firstCard!!.imageResourceId) {
                    // Cards match
                    secondCard!!.isMatched = true
                    adapter.notifyDataSetChanged()
                    // Check if all cards are matched
                    if (cards.all { it.isMatched }) {
                        Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Cards do not match
                    firstCard!!.isMatched = false
                    secondCard!!.isMatched = false
                    adapter.notifyDataSetChanged()
                }
                // Reset card selections
                firstCard = null
                secondCard = null
                // Enable card clicking again
                canClickCards = true
            }, 1000)
        }
    }
}
