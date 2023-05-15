package ph.stacktrek.stackilearningapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.model.CardModel

class CardAdapter(private val context: Context, private val cards: List<CardModel>) : BaseAdapter() {

    override fun getCount() = cards.size

    override fun getItem(position: Int) = cards[position]

    override fun getItemId(position: Int) = cards[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.card_image)
        val card = cards[position]
        if (card.isMatched) {
            // Load the image for a matched card
            Picasso.get().load(card.imageResourceId).resize(500, 500).onlyScaleDown().into(imageView)
        } else {
            // Load a placeholder image for an unmatched card
            Picasso.get().load(R.drawable.image_placeholder).resize(500, 500).onlyScaleDown().into(imageView)
        }
        return view
    }
}
