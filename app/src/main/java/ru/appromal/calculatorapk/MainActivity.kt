package ru.appromal.calculatorapk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var textTask:String = ""
    private lateinit var textTaskView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical)

        textTaskView = findViewById(R.id.task)

        findViewById<TextView>(R.id.btn_0).setOnClickListener{charAdd("0")}
        findViewById<TextView>(R.id.btn_1).setOnClickListener{charAdd("1")}
        findViewById<TextView>(R.id.btn_2).setOnClickListener{charAdd("2")}
        findViewById<TextView>(R.id.btn_3).setOnClickListener{charAdd("3")}
        findViewById<TextView>(R.id.btn_4).setOnClickListener{charAdd("4")}
        findViewById<TextView>(R.id.btn_5).setOnClickListener{charAdd("5")}
        findViewById<TextView>(R.id.btn_6).setOnClickListener{charAdd("6")}
        findViewById<TextView>(R.id.btn_7).setOnClickListener{charAdd("7")}
        findViewById<TextView>(R.id.btn_8).setOnClickListener{charAdd("8")}
        findViewById<TextView>(R.id.btn_9).setOnClickListener{charAdd("9")}
        findViewById<TextView>(R.id.btn_double).setOnClickListener{charAdd(".")}

        findViewById<TextView>(R.id.btn_open_bracket).setOnClickListener{charAdd("(")}
        findViewById<TextView>(R.id.btn_close_bracket).setOnClickListener{charAdd(")")}
        findViewById<TextView>(R.id.btn_share).setOnClickListener{charAdd("/")}
        findViewById<TextView>(R.id.btn_multiply).setOnClickListener{charAdd("*")}
        findViewById<TextView>(R.id.btn_minus).setOnClickListener{charAdd("-")}
        findViewById<TextView>(R.id.btn_sum).setOnClickListener{charAdd("+")}

        findViewById<TextView>(R.id.btn_back).setOnClickListener{charDelete()}
        findViewById<TextView>(R.id.btn_answer).setOnClickListener{taskAnswer()}
        findViewById<TextView>(R.id.btn_clear).setOnClickListener{taskClear()}
    }

    // Добавить символ
    private fun charAdd(str: String){
        textTask+=str
        textTaskView.text = textTask

    }
    // Удалить последний символ
    private fun charDelete(){
        val size = textTask.length
        println(size)
        if (size > 0) {
            textTask = textTask.substring(0, size - 1)
            textTaskView.text = textTask
        }
    }

    // Очистить пример
    private fun taskClear(){
        textTask=""
        textTaskView.text = textTask
    }

    // Вывести ответ
    private fun taskAnswer(){

    }

}