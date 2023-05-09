package ph.stacktrek.stackilearningapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.databinding.ActivityInteractiveBinding

class profileFragment : Fragment() {

    private lateinit var binding: ActivityInteractiveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_profile, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            profileFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}