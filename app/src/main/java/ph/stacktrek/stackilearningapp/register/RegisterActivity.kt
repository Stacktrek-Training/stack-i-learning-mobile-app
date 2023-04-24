package ph.stacktrek.stackilearningapp.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.dao.UserDAO
import ph.stacktrek.stackilearningapp.databinding.ActivityLoginBinding
import ph.stacktrek.stackilearningapp.databinding.ActivityRegisterBinding
import ph.stacktrek.stackilearningapp.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show()
            } else {
                val userDAO = UserDAO(this)
                val userId = userDAO.addUser(email, password)

                if (userId == -1L) {
                    Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
