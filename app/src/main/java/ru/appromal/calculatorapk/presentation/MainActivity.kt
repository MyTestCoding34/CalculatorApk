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
    private lateinit var mVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orientation()

        mVM = ViewModelProvider(this, MainViewModelFactory())[MainViewModel::class.java]
        textTaskView = findViewById(R.id.task)
        textAnswerView = findViewById(R.id.answer)

        mVM.resultLiveData.observe(this) { test ->
            textTaskView.text = test.dTask
            textAnswerView.text = test.dAnswer
        }

        findViewById<TextView>(R.id.btn_0).setOnClickListener{mVM.addSign(DAddSign(dSign = '0'))}
        findViewById<TextView>(R.id.btn_1).setOnClickListener{mVM.addSign(DAddSign(dSign = '1'))}
        findViewById<TextView>(R.id.btn_2).setOnClickListener{mVM.addSign(DAddSign(dSign = '2'))}
        findViewById<TextView>(R.id.btn_3).setOnClickListener{mVM.addSign(DAddSign(dSign = '3'))}
        findViewById<TextView>(R.id.btn_4).setOnClickListener{mVM.addSign(DAddSign(dSign = '4'))}
        findViewById<TextView>(R.id.btn_5).setOnClickListener{mVM.addSign(DAddSign(dSign = '5'))}
        findViewById<TextView>(R.id.btn_6).setOnClickListener{mVM.addSign(DAddSign(dSign = '6'))}
        findViewById<TextView>(R.id.btn_7).setOnClickListener{mVM.addSign(DAddSign(dSign = '7'))}
        findViewById<TextView>(R.id.btn_8).setOnClickListener{mVM.addSign(DAddSign(dSign = '8'))}
        findViewById<TextView>(R.id.btn_9).setOnClickListener{mVM.addSign(DAddSign(dSign = '9'))}
        findViewById<TextView>(R.id.btn_dot).setOnClickListener{mVM.addSign(DAddSign(dSign = '.'))}

        findViewById<TextView>(R.id.btn_open_bracket).setOnClickListener{mVM.addSign(DAddSign(dSign = '('))}
        findViewById<TextView>(R.id.btn_close_bracket).setOnClickListener{mVM.addSign(DAddSign(dSign = ')'))}
        findViewById<TextView>(R.id.btn_share).setOnClickListener{mVM.addSign(DAddSign(dSign = '/'))}
        findViewById<TextView>(R.id.btn_multiply).setOnClickListener{mVM.addSign(DAddSign(dSign = '*'))}
        findViewById<TextView>(R.id.btn_minus).setOnClickListener{mVM.addSign(DAddSign(dSign = '-'))}
        findViewById<TextView>(R.id.btn_sum).setOnClickListener{mVM.addSign(DAddSign(dSign = '+'))}

        findViewById<TextView>(R.id.btn_back).setOnClickListener{mVM.deleteChar()}
        findViewById<TextView>(R.id.btn_answer).setOnClickListener{mVM.returnAnswer(reset = true)}
        findViewById<TextView>(R.id.btn_clear).setOnClickListener{mVM.deleteTask()}
    }

    private fun orientation(){
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_vertical)
        else
            setContentView(R.layout.activity_horizontal)
    }
}