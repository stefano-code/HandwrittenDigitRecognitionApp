package com.android.test.handwrittendigitrecognition

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var drawingView: DrawingView
    private lateinit var classifier: DigitClassifier


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawingView)
        classifier = DigitClassifier(this)

        val btnPredict = findViewById<Button>(R.id.btnPredict)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnPredict.setOnClickListener {
            val bitmap = drawingView.getBitmap()
            bitmap?.let {
                val result = classifier.classify(it)
                txtResult.text = "Result: $result"
            }
        }

        btnClear.setOnClickListener {
            drawingView.clear()
            txtResult.text = "Result:"
        }
    }
}