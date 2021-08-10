package io.github.mrweb93.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private val TAG="MainActivity"


    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var trueAnswer =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate(Bundle?) called")

        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView  = findViewById(R.id.question_text_view)


        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            if (currentIndex==questionBank.size) Toast.makeText(this, ((trueAnswer/questionBank.size)*100).toString(), Toast.LENGTH_LONG).show()
            currentIndex = (currentIndex+1) % questionBank.size
            updateQuestion()
        }

        prevButton.setOnClickListener {
            if (currentIndex == 0) currentIndex = 0 else currentIndex = currentIndex-1
            updateQuestion()
        }

        questionTextView.setOnClickListener {

            if (currentIndex==questionBank.size-1)
                Toast.makeText(this, ((trueAnswer/questionBank.size)*100).toString(), Toast.LENGTH_LONG).show()

            currentIndex = (currentIndex+1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

        trueButton.isEnabled=true
        falseButton.isEnabled=true
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        var messageResId=R.string.incorrect_toast

        if (userAnswer == correctAnswer) {
            messageResId= R.string.correct_toast
            trueAnswer++
        } else
        {
            trueAnswer--
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        trueButton.isEnabled=false
        falseButton.isEnabled=false

    }
}