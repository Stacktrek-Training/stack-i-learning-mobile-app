package ph.stacktrek.stackilearningapp.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.databinding.FragmentHomeBinding

import ph.stacktrek.stackilearningapp.interactive.DragAndDropActivity
import ph.stacktrek.stackilearningapp.interactive.GuessGameActivity
import ph.stacktrek.stackilearningapp.interactive.HangmanActivity
import ph.stacktrek.stackilearningapp.interactive.MatchingGameActivity
import ph.stacktrek.stackilearningapp.interactive.MultipleChoiceActivity
import ph.stacktrek.stackilearningapp.interactive.PlaygroundActivity

class InteractiveFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        val multipleImage = binding.ivMultiplechoices
        val dragdropImage = binding.ivDragdrop
        val guessImage = binding.ivGuess
        val hangmanImage = binding.ivHangman
        val matchImage = binding.ivMatch

        val options = BitmapFactory.Options()
        options.inSampleSize = 4 // Scale down the image by a factor of 4

        Picasso.get()
            .load(R.drawable.category_multiplechoices)
            .config(Bitmap.Config.RGB_565)
            .resize(500, 500)
            .centerCrop()
            .into(multipleImage)

        Picasso.get()
            .load(R.drawable.category_dragdrop)
            .config(Bitmap.Config.RGB_565)
            .resize(500, 500)
            .centerCrop()
            .into(dragdropImage)

        Picasso.get()
            .load(R.drawable.category_guess)
            .config(Bitmap.Config.RGB_565)
            .resize(500, 500)
            .centerCrop()
            .into(guessImage)

        Picasso.get()
            .load(R.drawable.category_hangman)
            .config(Bitmap.Config.RGB_565)
            .resize(500, 500)
            .centerCrop()
            .into(hangmanImage)

        Picasso.get()
            .load(R.drawable.category_match)
            .config(Bitmap.Config.RGB_565) // Use 16-bit color depth to reduce memory usage
            .resize(500, 500) // Resize the image to a smaller size
            .centerCrop() // Crop the image to fill the ImageView
            .into(matchImage)


    }

    private fun setupClickListeners() {
        binding.btnGo.setOnClickListener {
            val goPlayground = Intent(requireContext(), PlaygroundActivity::class.java)
            startActivity(goPlayground)
        }

        binding.cvMultipleChoices.setOnClickListener {
            val goMultipleChoices =
                Intent(requireContext(), MultipleChoiceActivity::class.java)
            startActivity(goMultipleChoices)
        }

        binding.cvDragAndDrop.setOnClickListener {
            val goDragAndDrop = Intent(requireContext(), DragAndDropActivity::class.java)
            startActivity(goDragAndDrop)
        }

        binding.cvGuessThePicture.setOnClickListener {
            val goGuess = Intent(requireContext(), GuessGameActivity::class.java)
            startActivity(goGuess)
        }

        binding.cvHangman.setOnClickListener {
            val goHangman =
                Intent(requireContext(), HangmanActivity::class.java)
            startActivity(goHangman)
        }
        binding.cvMatch.setOnClickListener {
            val goMatch = Intent(requireContext(), MatchingGameActivity::class.java)
            startActivity(goMatch)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
