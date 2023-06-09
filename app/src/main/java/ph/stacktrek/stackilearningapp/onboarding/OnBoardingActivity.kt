package ph.stacktrek.stackilearningapp.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ph.stacktrek.stackilearningapp.R
import ph.stacktrek.stackilearningapp.adapters.OnBoardingViewPagerAdapter
import ph.stacktrek.stackilearningapp.login.LoginActivity
import ph.stacktrek.stackilearningapp.model.OnBoardingData

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var onBoardingViewPager2: ViewPager2
    var next: Button? = null
    var sharedPreferences: SharedPreferences? = null
    var position = 0 // Moved position variable outside onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (restoreData()) {
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )
        setContentView(R.layout.activity_on_boarding)

        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById(R.id.next)

        // Data to the model class
        val onBoardingDataList = listOf(
            OnBoardingData(
                "Learn Anytime,",
                "Anywhere, Accelerate Your Future and beyond.",
                R.drawable.ob_1,
            ),
            OnBoardingData(
                "Build Your Coding Skills with Real-World Examples",
                "Welcome to our app! We believe that the best way to learn to code is by doing. That's why we've created a series of real-world coding challenges that will help you build your skills in a practical way. From building websites to creating mobile apps, our challenges will teach you the skills you need to succeed",
                R.drawable.ob_2,
            ),
            OnBoardingData(
                "Learn to Code at Your Own Pace with Bite-Sized Lessons",
                "Welcome to our app! We know that learning to code can be intimidating, but it doesn't have to be. Our app is designed to help you learn to code at your own pace, with bite-sized lessons that you can complete anytime, anywhere. With our interactive learning approach, you'll be coding in no time",
                R.drawable.ob_3,
            ),
        )

        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingDataList)
        onBoardingViewPager2 = findViewById(R.id.screen_pager)
        onBoardingViewPager2.adapter = onBoardingViewPagerAdapter

        TabLayoutMediator(tabLayout, onBoardingViewPager2) { tab, position ->
            // Set tab text or icon here
        }.attach()

        onBoardingViewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                this@OnBoardingActivity
                    .position = position // Update the position variable
                if (position == onBoardingDataList.size - 1) {
                    next?.text = "Get Started"
                    next?.setOnClickListener {
                        if (next?.text == "Get Started") {
                            savePrefData()
                            val i = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(i)
                        }
                    }
                } else {
                    next?.text = "Next"
                }
            }
        })

        next?.setOnClickListener {
            if (position < onBoardingDataList.size - 1) {
                position++
                onBoardingViewPager2.currentItem = position
            }
        }
    }

    private fun savePrefData() {
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    private fun restoreData(): Boolean {
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }
}