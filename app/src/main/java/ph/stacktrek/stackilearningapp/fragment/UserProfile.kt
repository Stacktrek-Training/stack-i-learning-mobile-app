package ph.stacktrek.stackilearningapp.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.stacktrek.stackilearningapp.InteractiveFragment
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.databinding.FragmentUserProfileBinding
import ph.stacktrek.stackilearningapp.login.LoginActivity

class ProfileFragment : Fragment() {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
