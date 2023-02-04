package ru.appromal.calculatorapk.domain.models

/*
Класс служит для сохранения данных в стек для истории
stringTask              - строка примера
lastChar                - последний введёный знак
countSing               - количиство символов в примере(не более 100)
countNumbers            - количество введёных цыфр в числе(не более 15)
countNumbersAfterDot    - Чисел после запятой( не более 10)
isDouble                - Присутствует ли точка в числе(true да, false  нет)
dCountBracket           - Количество открытых скобок
isErrorEasy             - Ошибка не останавливающая вычисления
isErrorFatal            - Ошибка выводящаяся в сплывающем окне и остонавливающая ввод.
 */

data class DHistoryAddSings(
    val dStringTask: String,
    val dLastChar: String,
    val dCountSing: Int,
    val dCountNumbers: Int,
    val dCountNumbersAfterDot: Int,
    val dIsDouble:Boolean,
    val dCountBracket: Int,
)