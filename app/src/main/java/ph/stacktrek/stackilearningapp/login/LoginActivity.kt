package ph.stacktrek.stackilearningapp.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import ph.stacktrek.stackilearningapp.InteractiveActivity
import ph.stacktrek.stackilearningapp.LearningTypeActivity
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.dao.UserDAO
import ph.stacktrek.stackilearningapp.databinding.ActivityLoginBinding
import ph.stacktrek.stackilearningapp.register.RegisterActivity
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var userDAO: UserDAO = UserDAO(this)
    private val handler = Handler()

    private val launchRegister = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        val data = result.data

        val email = data!!.getStringExtra("email")
        val password = data.getStringExtra("password")


        if (email != null && password != null) {
            binding.etEmail.setText(email)
            binding.etPassword.setText(password)
            Snackbar.make(
                binding.root,
                "Registered ${data.getStringExtra("email")}",
                Snackbar.LENGTH_LONG,
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDAO = UserDAO(this)

        val email = intent.getStringExtra("email")
        if (email != null) {
            binding.etEmail.setText(email)
        }

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // validate the email here
                if (!isValidEmail(s.toString())) {

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
                    binding.loginSuccess.visibility = View.VISIBLE // Show the Lottie animation

                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        MotionToast.createToast(
                            this,
                            "Login Successfully!",
                            "Logged in.",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(this, R.font.spiegel_cd_bold),
                        )

                        val goLogin = Intent(applicationContext, LearningTypeActivity::class.java)
                        goLogin.putExtra("email", email)

                        val bundle = Bundle()
                        bundle.putString("bundle_email", email)
                        goLogin.putExtras(bundle)

                        startActivity(goLogin)
                        finish()

                        binding.loginSuccess.visibility = View.GONE // Hide the Lottie animation
                    }, 3000) // Delay the execution by 3 seconds (3000 milliseconds)
                } else {
                    MotionToast.createToast(
                        this,
                        "Login Failed!",
                        "Invalid email or password!",
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(this, R.font.spiegel_cd_bold),
                    )
                }
            } else {
                MotionToast.createToast(
                    this,
                    "Login Failed!",
                    "Fields cannot be empty!",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this, R.font.spiegel_cd_bold),
                )
            }
        }

        binding.btnRegister.setOnClickListener {
            val goToRegister = Intent(
                applicationContext,
                RegisterActivity::class.java,
            )
            launchRegister.launch(goToRegister)
            finish()
        }
    }
}
