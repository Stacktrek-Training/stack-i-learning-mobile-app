package ph.stacktrek.stackilearningapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import ph.stacktrek.stackilearningapp.InteractiveActivity
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.dao.UserDAO
import ph.stacktrek.stackilearningapp.databinding.ActivityLoginBinding
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

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // validate the email here
                if (!isValidEmail(s.toString())) {
                    binding.etEmail.error = "Invalid email"
                } else {
                    binding.etEmail.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // do nothing
            }

            fun isValidEmail(email: String): Boolean {
                val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
                return email.matches(emailRegex)
            }

        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // validate the password here
            }

            override fun afterTextChanged(s: Editable?) {
                // do nothing
            }
        })

        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isBlank()) {
                binding.etEmail.error = "Email cannot be empty"
            } else {
                binding.etEmail.error = null
            }

            if (password.isBlank()) {
                binding.etPassword.error = "Password cannot be empty"
            } else {
                binding.etPassword.error = null
            }

            if (binding.etEmail.error == null && binding.etPassword.error == null) {
                if (userDAO.validateUser(email, password)) {
                    val goLogin = Intent(applicationContext, InteractiveActivity::class.java)
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
            } else {
                Snackbar.make(binding.root,
                    "Please input valid credentials",
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