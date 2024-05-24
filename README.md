# Tickets Analyzer

## Описание

Эта программа написана на языке Java и предназначена для анализа данных о билетах на авиарейсы. Она читает данные из файла `tickets.json` и рассчитывает:
- Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика.
- Разницу между средней ценой и медианой для полетов между городами Владивосток и Тель-Авив.

## Требования

- Java 8 или выше.
- Библиотека GSON для работы с JSON (скачать можно [здесь](https://github.com/google/gson)).

## Установка

1. Склонируйте репозиторий или загрузите файлы проекта.
2. Скачайте библиотеку GSON и поместите ее в директорию `lib`.

## Компиляция и запуск

1. Сделайте файл `run.sh` исполняемым:
    ```sh
    chmod +x run.sh
    ```

2. Запустите скрипт:
    ```sh
    ./run.sh
    ```

## Файл tickets.json

Формат файла `tickets.json` должен быть следующим:

```json
{
  "tickets": [
    {
      "origin": "VVO",
      "origin_name": "Владивосток",
      "destination": "TLV",
      "destination_name": "Тель-Авив",
      "departure_date": "12.05.18",
      "departure_time": "16:20",
      "arrival_date": "12.05.18",
      "arrival_time": "22:10",
      "carrier": "TK",
      "stops": 3,
      "price": 12400
    },
    ...
  ]
}
```
## Скриншот выполнения
![photo_2024-05-20_21-51-08](https://github.com/Kolleper/TicketAnalyzer/assets/61471977/6b8b0236-3ef8-45cd-92e6-b71c57ca281c)
