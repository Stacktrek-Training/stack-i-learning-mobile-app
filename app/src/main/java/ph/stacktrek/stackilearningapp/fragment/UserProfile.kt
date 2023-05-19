package ph.stacktrek.stackilearningapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.stacktrek.stackilearningapp.databinding.FragmentUserProfileBinding
import ph.stacktrek.stackilearningapp.profile.EditProfileActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private var userEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the user's email from the arguments
        userEmail = arguments?.getString("bundle_email")

        // Display the user's email in a TextView or perform any other actions with it
        binding.emailTextView.text = userEmail

        binding.profilesetting.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        binding.backbutton.setOnClickListener {
            val intent = Intent(requireContext(), InteractiveFragment::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
