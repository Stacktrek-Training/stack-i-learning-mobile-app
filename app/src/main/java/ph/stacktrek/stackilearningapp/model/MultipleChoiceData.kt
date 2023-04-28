package ph.stacktrek.stackilearningapp.model

import ph.stacktrek.stackilearningapp.R

class MultipleChoicesData {
    companion object {
        val questions = arrayOf(
            "What is HTML?",
            "HTML stands for __________",
            "What is the correct syntax of doctype in HTML5?",
            "How do we write comments in HTML?",
            "Among the following, which is the HTML paragraph tag?",
        )

        val choices = arrayOf(
            arrayOf("HTML describes the structure of a webpage", "HTML is the standard markup language mainly used to create web pages", "HTML consists of a set of elements that helps the browser how to view the content", "All of the mentioned"),
            arrayOf("HyperText Markup Language", "HyperText Machine Language", "HyperText Marking Language", "HighText Marking Language"),
            arrayOf("</doctype html>", "<doctype html>", "<doctype html!>", "<!doctype html>"),
            arrayOf("</…….>", "<!……>", "</……/>", "<…….!>"),
            arrayOf("<p>", "<pre>", "<hr>", "<a>"),
        )

        val correctAnswers = arrayOf(
            "All of the mentioned",
            "HyperText Markup Language",
            "<!doctype html>",
            "<!……>",
            "<p>",
        )

        val questionImages = arrayOf(
            R.drawable.html_img,
        )
    }
}
