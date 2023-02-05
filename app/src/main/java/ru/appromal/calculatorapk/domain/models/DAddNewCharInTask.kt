package ru.appromal.calculatorapk.domain.models

import ru.appromal.calculatorapk.domain.dll.Stack

data class DAddNewCharInTask (
    val dLastStack: Stack<DHistoryAddSigns>,
    val dNewChar: DSendNewCharTask
)