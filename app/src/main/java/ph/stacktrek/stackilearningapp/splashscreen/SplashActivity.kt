package ph.stacktrek.stackilearningapp.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.onboarding.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        findViewById<ImageView>(R.id.logo).startAnimation(splashAnimation)

        Handler().postDelayed({
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }, 5000) // delay for 5 seconds
    }
}
