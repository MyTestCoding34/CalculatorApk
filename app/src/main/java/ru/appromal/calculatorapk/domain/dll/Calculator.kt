package ru.appromal.calculatorapk.domain.dll

import ru.appromal.calculatorapk.domain.models.DReturnAnswerTask
import ru.appromal.calculatorapk.domain.models.EError
import java.text.DecimalFormat

class Calculator(private val stackTask: Stack<String>) {
    // Создаём стек под дробные цифры
    private val stackDouble: Stack<Double> = Stack()
    // Создаём стек под мат знаки
    private val stackSign: Stack<String> = Stack()
    // Размер стека с знаками
    private var sizeStackSign = 0
    // Переменная под значения с массива Очереди в который поместили разбитую строку
    private lateinit var whenString: String

    fun execute(): DReturnAnswerTask {
        calculator()
        val answer = roundNumber(stackDouble.peek())
        return DReturnAnswerTask(dStringAnswer = answer)
    }

    private fun calculator(){
        // Переменная под временное хранение мат. знаков
        var tempSign: String
        // Переменная для запуска цикла чтения
        var whileBoolean = true

        while (whileBoolean) {
            whenString = stackTask.peek()
            if (whenString == "(") {
                remake()
            } else if (whenString == ")") {
                tempSign = stackSign.pop()
                if (tempSign == "(") {
                    sizeStackSign--
                    stackTask.pop()
                } else {
                    stackSign.push(tempSign)
                    lastAction()
                }
            } else if (whenString == "/" || whenString == "*" || whenString == "-" || whenString == "+") {
                if (sizeStackSign <= 0 || stackSign.peek() == "("
                    || signPriority(whenString, stackSign.peek()) == 1)
                    remake()
                else if (signPriority(whenString, stackSign.peek()) == 2)
                    lastAction()
                else
                    lastAction()
            } else {
                stackDouble.push(whenString.toDouble())
                stackTask.pop()
            }
            if (stackTask.isEmpty())
                whileBoolean = false

        }
        if (!stackSign.isEmpty()) {
            for (i in 1..sizeStackSign){
                lastAction()
            }
        }
    }

    //Функция определяет приоритет мат. знака над предыдущим в стеке
    private fun signPriority(value1: String, value2: String): Int {
        if (prioritySign(value1) > prioritySign(value2))
            return 1
        else if (prioritySign(value1) == prioritySign(value2))
            return 2
        return 3
    }

    // Функция возвращает приоритет мат. знака
    private fun prioritySign(value: String): Int {
        return when (value) {
            ")" -> 0
            "(" -> 0
            "+" -> 1
            "-" -> 1
            "*" -> 2
            "/" -> 2
            else -> 0
        }
    }

    //Функция выполняет математическое действие
    private fun mathematicalAction(num2: Double, num1: Double, sing: String): Double {
        return when (sing) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 == 0.0) {
                    throw Exception(EError.BY_ZERO.isErrorTextEnum)
                }
                num1 / num2
            }
            else -> 0.0
        }
    }

    // Функция для математического действия последних двух чисел в стека и перезаписи
    // получившегося значения в стек. Так же уменьшаем значение переменной размер
    // стека знаков
    private fun lastAction() {
        sizeStackSign--
        stackDouble.push(mathematicalAction(stackDouble.pop(), stackDouble.pop(), stackSign.pop()))
    }

    // Функция переносит математичиский знак с стека с заданием с стек с знаками
    private fun remake() {
        sizeStackSign++
        stackSign.push(whenString)
        stackTask.pop()
    }
    // TODO Функцию нужно будет переписать, написать самостоятельное округление
    private fun roundNumber(answerText: Double ):String{
        if (answerText<999999999999999)
            return DecimalFormat("0.##########").format(answerText)
        return answerText.toString()
    }
}