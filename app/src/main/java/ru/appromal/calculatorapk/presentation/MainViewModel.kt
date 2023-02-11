package ru.appromal.calculatorapk.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.appromal.calculatorapk.domain.models.DAddSign
import ru.appromal.calculatorapk.domain.usecase.UCUnite
import ru.appromal.calculatorapk.presentation.models.DAnswerLiveData

class MainViewModel(private val unite: UCUnite): ViewModel() {
    val resultLiveData = MutableLiveData<DAnswerLiveData>()
    private var answer = ""
    private var task = ""

    fun addSign(dAddSign: DAddSign){
        unite.addSign(DAddSign(dSign = dAddSign.dSign))
        getAnswer()
        task =  unite.returnText()
        resultLiveData.value =DAnswerLiveData(dTask =task, dAnswer = answer)
    }

    fun deleteTask() {
        unite.deleteTask()
        resultLiveData.value =DAnswerLiveData(dTask ="", dAnswer = "")
    }

    fun deleteChar() {
        unite.deleteChar()
        getAnswer()
        task =  unite.returnText()
        resultLiveData.value =DAnswerLiveData(dTask =task, dAnswer = answer)
    }

    fun returnAnswer(reset: Boolean = false) {
        getAnswer(reset = true)
        resultLiveData.value =DAnswerLiveData(dTask ="$task $answer", dAnswer = "")
    }

    private fun getAnswer(reset: Boolean = false) {
        answer = unite.returnAnswer(reset)
    }
}