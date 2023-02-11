package ru.appromal.calculatorapk.presentation

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import ru.appromal.calculatorapk.R
import ru.appromal.calculatorapk.domain.models.DAddSign

class MainActivity : AppCompatActivity() {

    private lateinit var textTaskView: TextView
    private lateinit var textAnswerView: TextView
    private val buttons =arrayOf( R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
        R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
        R.id.btn_dot, R.id.btn_open_bracket, R.id.btn_close_bracket,
        R.id.btn_share, R.id.btn_multiply, R.id.btn_minus, R.id.btn_sum)
    private val signs = arrayOf( '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '.', '(', ')', '/', '*', '-', '+')

    private lateinit var mVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> R.layout.activity_vertical
            else -> R.layout.activity_horizontal
        })

        mVM = ViewModelProvider(this, MainViewModelFactory())[MainViewModel::class.java]
        textTaskView = findViewById(R.id.task)
        textAnswerView = findViewById(R.id.answer)

        mVM.resultLiveData.observe(this) { test ->
            textTaskView.text = test.dTask
            textAnswerView.text = test.dAnswer
        }

        for (i in buttons.indices) {
            findViewById<TextView>(buttons[i]).setOnClickListener {
                mVM.addSign(DAddSign(dSign = signs[i]))
            }
        }

        findViewById<TextView>(R.id.btn_back).setOnClickListener{mVM.deleteChar()}
        findViewById<TextView>(R.id.btn_answer).setOnClickListener{mVM.returnAnswer(reset = true)}
        findViewById<TextView>(R.id.btn_clear).setOnClickListener{mVM.deleteTask()}
    }
}