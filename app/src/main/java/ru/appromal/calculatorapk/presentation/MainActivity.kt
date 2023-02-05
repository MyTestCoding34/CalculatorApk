package ru.appromal.calculatorapk.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.appromal.calculatorapk.R
import ru.appromal.calculatorapk.domain.usecase.UCUnite
import ru.appromal.calculatorapk.domain.models.DAddSign

class MainActivity : AppCompatActivity() {

    private val unite: UCUnite = UCUnite()
    private lateinit var textTaskView: TextView
    private lateinit var textAnswerView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical)

        textTaskView = findViewById(R.id.task)
        textAnswerView = findViewById(R.id.answer)

        findViewById<TextView>(R.id.btn_0).setOnClickListener{unite.addSign(DAddSign(dSign = '0'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_1).setOnClickListener{unite.addSign(DAddSign(dSign = '1'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_2).setOnClickListener{unite.addSign(DAddSign(dSign = '2'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_3).setOnClickListener{unite.addSign(DAddSign(dSign = '3'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_4).setOnClickListener{unite.addSign(DAddSign(dSign = '4'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_5).setOnClickListener{unite.addSign(DAddSign(dSign = '5'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_6).setOnClickListener{unite.addSign(DAddSign(dSign = '6'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_7).setOnClickListener{unite.addSign(DAddSign(dSign = '7'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_8).setOnClickListener{unite.addSign(DAddSign(dSign = '8'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_9).setOnClickListener{unite.addSign(DAddSign(dSign = '9'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_dot).setOnClickListener{unite.addSign(DAddSign(dSign = '.'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}

        findViewById<TextView>(R.id.btn_open_bracket).setOnClickListener{unite.addSign(DAddSign(dSign = '('))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_close_bracket).setOnClickListener{unite.addSign(DAddSign(dSign = ')'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_share).setOnClickListener{unite.addSign(DAddSign(dSign = '/'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_multiply).setOnClickListener{unite.addSign(DAddSign(dSign = '*'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_minus).setOnClickListener{unite.addSign(DAddSign(dSign = '-'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_sum).setOnClickListener{unite.addSign(DAddSign(dSign = '+'))
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}

        findViewById<TextView>(R.id.btn_back).setOnClickListener{unite.deleteChar()
            textTaskView.text = unite.returnText()
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_answer).setOnClickListener{
            textAnswerView.text = unite.returnAnswer()}
        findViewById<TextView>(R.id.btn_clear).setOnClickListener{unite.deleteTask()
            textTaskView.text = unite.returnText()
            textAnswerView.text = ""}
    }
}