package ph.stacktrek.stackilearningapp.interactive

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import ph.stacktrek.stackilearningapp.R

class KeyboardView(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val mLetters = arrayOf(
        "A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J ", "K ", "L ", "M ",
        "N ", "O ", "P ", "Q ", "R ", "S ", "T ", "U ", "V ", "W ", "X ", "Y ", "Z ",
    )
    private val mLetterViews: MutableList<TextView> = ArrayList()
    private val mTempRect = Rect()
    private var mCellWidth = 0
    private var mCellHeight = 0

    interface OnLetterClickListener {
        fun onLetterClick(letter: String)
    }

    private var onLetterClickListener: OnLetterClickListener? = null

    fun setOnLetterClickListener(listener: (String) -> Unit) {
        for (letterView in mLetterViews) {
            letterView.setOnClickListener { view: View? ->
                if (view is TextView) {
                    val letter = view.text.toString().trim()
                    listener(letter)
                }
            }
        }
    }

    init {
        for (i in mLetters.indices) {
            val letterView = TextView(context)
            letterView.text = mLetters[i]
            letterView.gravity = Gravity.CENTER // set gravity to center
            letterView.setBackgroundResource(R.drawable.key_background)

            // Add padding to center the letter within the background
            val horizontalPadding = resources.getDimensionPixelSize(R.dimen.letter_button_horizontal_padding)
            val verticalPadding = resources.getDimensionPixelSize(R.dimen.letter_button_vertical_padding)
            letterView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)

            letterView.setOnClickListener {
                onLetterClickListener?.onLetterClick(mLetters[i].trim())
            }
            mLetterViews.add(letterView)
            addView(letterView)
        }
        ViewCompat.setImportantForAccessibility(this, ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        mCellWidth = width / NUM_COLS
        mCellHeight = height / NUM_ROWS
        val cellSizeSpec =
            MeasureSpec.makeMeasureSpec(Math.min(mCellWidth, mCellHeight), MeasureSpec.EXACTLY)
        for (letterView in mLetterViews) {
            measureChild(letterView, cellSizeSpec, cellSizeSpec)
        }
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in mLetterViews.indices) {
            val letterView = mLetterViews[i]
            val row = i / NUM_COLS
            val col = i % NUM_COLS
            val cellLeft = col * mCellWidth
            val cellTop = row * mCellHeight
            val cellRight = cellLeft + mCellWidth
            val cellBottom = cellTop + mCellHeight
            mTempRect[cellLeft, cellTop, cellRight] = cellBottom
            letterView.layout(mTempRect.left, mTempRect.top, mTempRect.right, mTempRect.bottom)
        }
    }

    companion object {
        private const val NUM_ROWS = 4
        private const val NUM_COLS = 7
    }
}
