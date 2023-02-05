package ru.appromal.calculatorapk.domain.dll
/*
Класс помещает строку с математическим примером в стек в реверсивном порядке, в дальнейшем
 будет удобней работать.
 */
class StringToStack {
    fun execute(task: String, stackTask: Stack<String>){
        // Объявляем Флаг, будет служить для определения закончилось ли число или нет.
        // При значение в истена значение всегда добавляется в новую ячейку
        var intBoolean = true
        stackTask.clearAll()

        for(valueChar in task){
            // Проверяем является ли символ цифрой или точкой
            if(valueChar in '0'..'9' || valueChar == '.'){
                // Если цифра встретилась впервые то помещаем ёе в новую ячейку
                // и устанавливаем значение Флага в ложь, если дальше попадётся цифра
                // просто будем дописывать в этуже ячейку
                if(intBoolean) {
                    stackTask.push(valueChar.toString())
                    intBoolean = false
                }
                else
                    stackTask.push(stackTask.pop() + valueChar.toString())
            }
            // Любой знак записываем в новую ячейку и будем устанавливать Флаг в истину
            // ( возможно перед знаком флаг был ложь, дозаписывая цифры в ячейку
            else{
                stackTask.push(valueChar.toString())
                intBoolean = true
            }
        }
        stackTask.reverse()
    }
}