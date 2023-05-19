package ph.stacktrek.stackilearningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ph.stacktrek.stackilearningapp.databinding.ActivityMainBinding
import ph.stacktrek.stackilearningapp.fragment.InteractiveFragment
import ph.stacktrek.stackilearningapp.fragment.ProfileFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener {
            when (it) {
                R.id.nav_home -> replaceFragment(InteractiveFragment())
                R.id.nav_profile -> replaceFragment(ProfileFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}