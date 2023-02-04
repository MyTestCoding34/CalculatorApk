package ru.appromal.calculatorapk.domain.models

import ru.appromal.calculatorapk.domain.dll.Stack

data class DReturnNewStackWithTask(
    val dLastStack: Stack<DHistoryAddSings>,
    val dError: EError
)
