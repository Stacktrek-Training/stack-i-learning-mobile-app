package ph.stacktrek.stackilearningapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.model.OnBoardingData

class OnBoardingViewPagerAdapter(private val context: Context, private val onBoardingDataList: List<OnBoardingData>) :
    RecyclerView.Adapter<OnBoardingViewPagerAdapter.OnBoardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, parent, false)
        return OnBoardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingDataList[position])
    }

    override fun getItemCount(): Int {
        return onBoardingDataList.size
    }

    inner class OnBoardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.ob_imageView)
        private val titleTextView: TextView = itemView.findViewById(R.id.ob_title)
        private val descTextView: TextView = itemView.findViewById(R.id.ob_desc)

        fun bind(onBoardingData: OnBoardingData) {
            Picasso.get()
                .load(onBoardingData.imageUrl)
                .resize(500, 500) //set the size of the image
                .into(imageView)
            titleTextView.text = onBoardingData.title
            descTextView.text = onBoardingData.desc
        }
    }
}
