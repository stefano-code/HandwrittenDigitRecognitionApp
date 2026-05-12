package com.android.test.handwrittendigitrecognition

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class DigitClassifier(context: Context) {

    private var interpreter: Interpreter

    init {
        val model = loadModelFile(context)
        interpreter = Interpreter(model)
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd("digit_recognition.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val channel = inputStream.channel
        return channel.map(
            FileChannel
                .MapMode.READ_ONLY,
            fileDescriptor.startOffset,
            fileDescriptor.declaredLength
        )
    }

    fun classify(bitmap: Bitmap): Int {
        val resized = Bitmap.createScaledBitmap(bitmap, 28, 28, false)

        val input = Array(1) { FloatArray(28 * 28) }

        for (i in 0 until 28) {
            for (j in 0 until 28) {
                val pixel = resized.getPixel(j, i)
                val value = Color.red(pixel) / 255.0f
                input[0][i * 28 + j] = value
            }
        }

        val output = Array(1) { FloatArray(10) }

        interpreter.run(input, output)

        return output[0].indices.maxByOrNull { output[0][it] } ?: -1
    }
}