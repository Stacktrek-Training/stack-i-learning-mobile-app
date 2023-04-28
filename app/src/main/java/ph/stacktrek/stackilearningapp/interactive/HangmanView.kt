package ph.stacktrek.stackilearningapp.interactive

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class HangmanView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private var remainingMoves = 7

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Set the paint color and stroke width
        paint.color = Color.BLACK
        paint.strokeWidth = 10f

        // Calculate the horizontal center of the view
        val centerX = width / 2f

        // Draw the scaffold
        canvas?.drawLine(centerX - 100f, 400f, centerX + 100f, 400f, paint)
        canvas?.drawLine(centerX, 400f, centerX, 100f, paint)
        canvas?.drawLine(centerX, 100f, centerX + 200f, 100f, paint)
        canvas?.drawLine(centerX + 200f, 100f, centerX + 200f, 150f, paint)

        // Draw the head
        if (remainingMoves <= 6) {
            canvas?.drawCircle(centerX + 200f, 50f, 50f, paint)
        }

        // Draw the body
        if (remainingMoves <= 5) {
            canvas?.drawLine(centerX + 200f, 100f, centerX + 200f, 300f, paint)
        }

        // Draw the left arm
        if (remainingMoves <= 4) {
            canvas?.drawLine(centerX + 200f, 150f, centerX + 150f, 200f, paint)
        }

        // Draw the right arm
        if (remainingMoves <= 3) {
            canvas?.drawLine(centerX + 200f, 150f, centerX + 250f, 200f, paint)
        }

        // Draw the left leg
        if (remainingMoves <= 2) {
            canvas?.drawLine(centerX + 200f, 300f, centerX + 150f, 400f, paint)
        }

        // Draw the right leg
        if (remainingMoves <= 1) {
            canvas?.drawLine(centerX + 200f, 300f, centerX + 250f, 400f, paint)
        }
    }

    fun setRemainingMoves(moves: Int) {
        remainingMoves = moves
        invalidate()
    }
}
