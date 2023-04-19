package ph.stacktrek.stackilearningapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.dao.UserDAO
import ph.stacktrek.stackilearningapp.databinding.ActivityLoginBinding
import ph.stacktrek.stackilearningapp.quiz.MultipleChoiceActivity
import ph.stacktrek.stackilearningapp.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var userDAO: UserDAO = UserDAO(this)

    private val launchRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data

        val email = data!!.getStringExtra("email")
        val password = data.getStringExtra("password")

        if (email != null && password != null) {
            binding.etEmail.setText(email)
            binding.etPassword.setText(password)
            Snackbar.make(
                binding.root, "Registered ${data!!.getStringExtra("email")}",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDAO = UserDAO(this)

        binding.btnLogin.setOnClickListener {

            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()

            if (userDAO.validateUser(email, password)) {
                val goLogin = Intent(applicationContext, MultipleChoiceActivity::class.java)
                goLogin.putExtra("email", email)

                val bundle = Bundle()
                bundle.putString("bundle_email", email)
                goLogin.putExtras(bundle)

                startActivity(goLogin)
                finish()

            }
            else{
                Snackbar.make(binding.root,
                    "Invalid email or password",
                    Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            val goToRegister = Intent(
                applicationContext,
                RegisterActivity::class.java
            )

            launchRegister.launch(goToRegister)
        }
    }
}