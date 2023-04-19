package ph.stacktrek.stackilearningapp.model

class DragAndDropData(val question: String, val snippet: String, val answer: String, val choices: List<String>) {
    companion object {
        val questionsList = listOf(
            DragAndDropData(
                "Removes one or more records from a table:",
                "_____ FROM table_name WHERE condition?",
                "DELETE",
                listOf("DELETE", "UPDATE", "SELECT", "SET")
            ),
            DragAndDropData(
                "Returns a result set of records, from one or more tables:",
                "_____ * FROM table_name",
                "SELECT",
                listOf("WHERE", "SELECT", "BETWEEN", "AS")
            )
        )
    }
}
