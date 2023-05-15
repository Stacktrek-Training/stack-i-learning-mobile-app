package ph.stacktrek.stackilearningapp.model

data class CardModel(
    val id: Int,
    val imageResourceId: Int,
    var isMatched: Boolean = false
)
