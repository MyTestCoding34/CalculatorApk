package ru.appromal.calculatorapk.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.appromal.calculatorapk.R
import ru.appromal.calculatorapk.domain.usecase.UCUnite
import ru.appromal.calculatorapk.domain.models.DSendNewCharTask

class MainActivity : AppCompatActivity() {


    private val unite: UCUnite = UCUnite()
    private lateinit var textTaskView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical)

        textTaskView = findViewById(R.id.task)


        findViewById<TextView>(R.id.btn_0).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "0", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_1).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "1", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_2).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "2", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_3).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "3", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_4).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "4", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_5).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "5", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_6).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "6", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_7).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "7", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_8).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "8", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_9).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "9", dType = "INT"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_dot).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = ".", dType = "DOT"))
            textTaskView.text = unite.returnText()}

        findViewById<TextView>(R.id.btn_open_bracket).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "(", dType = "BR_OPEN"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_close_bracket).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = ")", dType = "BR_CLOSE"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_share).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "/", dType = "SING"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_multiply).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "*", dType = "SING"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_minus).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "-", dType = "SING"))
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_sum).setOnClickListener{unite.addSing(DSendNewCharTask(dSing = "+", dType = "SING"))
            textTaskView.text = unite.returnText()}

        findViewById<TextView>(R.id.btn_back).setOnClickListener{unite.deleteChar()
            textTaskView.text = unite.returnText()}
        findViewById<TextView>(R.id.btn_answer).setOnClickListener{answerTask()}
        findViewById<TextView>(R.id.btn_clear).setOnClickListener{unite.deleteTask()
            textTaskView.text = unite.returnText()}
    }

    private fun answerTask(){
    }

}