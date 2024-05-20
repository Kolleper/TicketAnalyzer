#!/bin/bash

# Создание директории bin для скомпилированных классов
mkdir -p bin

# Компиляция программы
javac -cp lib/gson-2.8.6.jar -d bin src/com/example/ticketsanalyzer/*.java
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi

# Запуск программы
java -cp bin:lib/gson-2.8.6.jar com.example.ticketsanalyzer.Main data/tickets.json
if [ $? -ne 0 ]; then
    echo "Execution failed."
    exit 1
fi