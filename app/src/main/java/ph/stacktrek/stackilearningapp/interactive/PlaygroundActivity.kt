package ph.stacktrek.stackilearningapp.interactive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import ph.stacktrek.stackilearningapp.R

class PlaygroundActivity : AppCompatActivity() {

    private lateinit var mLanguageSpinner: Spinner
    private lateinit var mCodeEditor: EditText
    private lateinit var mRunButton: Button
    private lateinit var mOutputWebView: WebView

    private val mLanguageList = arrayOf("Java", "Python", "C++", "JavaScript")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playground)

        mLanguageSpinner = findViewById(R.id.language_spinner)
        val languageAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mLanguageList)
        mLanguageSpinner.adapter = languageAdapter

        mCodeEditor = findViewById(R.id.code_editor)
        //mCodeEditor.setSyntaxHighlightingRules(getSyntaxHighlightingRules("Java")) // set default language to Java

        mRunButton = findViewById(R.id.run_button)
        mRunButton.setOnClickListener {
            val code = mCodeEditor.text.toString()
            val language = mLanguageSpinner.selectedItem.toString()
            //val output = runCode(code, language)
            //mOutputWebView.loadData(output, "text/html", "UTF-8")
        }

        mOutputWebView = findViewById(R.id.output_webview)
    }
}
