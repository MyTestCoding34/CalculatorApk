package ru.appromal.calculatorapk.domain.models

enum class EError(val isErrorEasyEnum: Boolean, val isErrorFatalEnum: Boolean, val isErrorTextEnum: String) {
    MAX_SING(false, true,
        "Максимально допустимое количество не более 100 знаков в примере!"),
    MAX_NUMBERS(false, true,
        "Максимальное количество цифр в числе не более 15!"),
    MAX_NUMBERS_DOUBLE(false, true,
        "Максимальное количество знаков после запятой не более 10!"),
    SING_END(true, false,
        "Пример не должен заканчиваться на математический знак!"),
    BRACKET(true, true,
        "Количество открывающихся и закрывающихся скобок разное"),
    BY_ZERO(true, false,
        "На ноль делить нельзя!"),
    NO_ERROR(false, false,
        "No error, default mess.")
}