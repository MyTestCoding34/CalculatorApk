package ru.appromal.calculatorapk.domain.dll
/*
Класс под стек на базе изменяемого List для любого типа данных
push()          - Добавляет значение в конец стека
pop()           - Возвращаем последнее значение в стеке и уменьшаем его на 1
peek()          - Возвращает последнее значение в стеке, не уменьшая его
isEmpty()       - Проверяем пуст ли стек, если пуст возвращаем true
reverse()       - Перезаписываем стек в обратном подрядке
 */
class Stack<T> {
    private val stack: MutableList<T> = mutableListOf()
    fun push(value: T){
        stack.add(value)
    }
    fun pop(): T = stack.removeLast()
    fun peek(): T =  stack.last()
    fun isEmpty(): Boolean = stack.isEmpty()
    fun reverse() {
        stack.reverse()
    }
    fun clearAll(){
        stack.clear()
    }
    // TODO тестовая функция для вывода
    fun readAll(){
        println(stack.toString())
    }
}