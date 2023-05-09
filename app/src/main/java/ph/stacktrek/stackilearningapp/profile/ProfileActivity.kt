package ph.stacktrek.stackilearningapp.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.stacktrek.stackilearningapp.InteractiveActivity
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.databinding.ActivityProfileBinding
import ph.stacktrek.stackilearningapp.databinding.ActivityRegisterBinding
import ph.stacktrek.stackilearningapp.login.LoginActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profilesetting.setOnClickListener(){
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.backbutton.setOnClickListener(){
            val intent = Intent(this, InteractiveActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}