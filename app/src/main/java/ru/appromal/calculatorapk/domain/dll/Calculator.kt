package ru.appromal.calculatorapk.domain.dll

import ru.appromal.calculatorapk.domain.models.DReturnAnswerTask
import ru.appromal.calculatorapk.domain.models.EError

class Calculator(private val stackTask: Stack<String>) {
    // Создаём стек под дробные цифры
    private val stackDouble: Stack<Double> = Stack()
    // Создаём стек под мат знаки
    private val stackSing: Stack<String> = Stack()
    // Размер стека с знаками
    private var sizeStkSing = 0
    // Переменная под значения с массива Очереди в который поместили разбитую строку
    private lateinit var whenString: String
    // Переменная под ошибку
    private var errorAnswer: EError = EError.NO_ERROR

    fun execute(): DReturnAnswerTask {
        calculator()
        return DReturnAnswerTask(dStringAnswer = stackDouble.peek().toString(), dError = errorAnswer)
    }

    private fun calculator(){
        // Переменная под временное хранение мат. знаков
        var tempSing: String
        // Переменная для запуска цикла чтения
        var whileBoolean = true

        while (whileBoolean) {
            whenString = stackTask.peek()
            if (whenString == "(") {
                remake()
            } else if (whenString == ")") {
                tempSing = stackSing.pop()
                if (tempSing == "(") {
                    sizeStkSing--
                    stackTask.pop()
                } else {
                    stackSing.push(tempSing)
                    lastAction()
                }
            } else if (whenString == "/" || whenString == "*" || whenString == "-" || whenString == "+") {
                if (sizeStkSing <= 0 || stackSing.peek() == "("
                    || singPriority(whenString, stackSing.peek()) == 1)
                    remake()
                else if (singPriority(whenString, stackSing.peek()) == 2)
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
        if (!stackSing.isEmpty()) {
            for (i in 1..sizeStkSing){
                lastAction()
            }
        }
    }

    //Функция определяет приоритет мат. знака над предыдущим в стеке
    private fun singPriority(value1: String, value2: String): Int {
        if (prioritySing(value1) > prioritySing(value2))
            return 1
        else if (prioritySing(value1) == prioritySing(value2))
            return 2
        return 3
    }

    // Функция возвращает приоритет мат. знака
    private fun prioritySing(value: String): Int {
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
                    errorAnswer = EError.BY_ZERO
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
        sizeStkSing--
        stackDouble.push(mathematicalAction(stackDouble.pop(), stackDouble.pop(), stackSing.pop()))
    }

    // Функция переносит математичиский знак с стека с заданием с стек с знаками
    private fun remake() {
        sizeStkSing++
        stackSing.push(whenString)
        stackTask.pop()
    }
}