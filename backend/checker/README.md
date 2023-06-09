# Checker js
Утилита для проверки верстки/js-кода

```shell
# Локально:
# 1. Установить
npm install

# 2. (optional) Удалить старый тест 
rm -rf ./submission/*

# 3. Скопировать нужный пример
cp -r ./example/1/* ./submission

# 4. Запустить
npm run unit
# или
npm run comparing 
# Для интеграционного теста запускается полноценный браузер
# Предупреждение: при запуске локально, на скриншотах в 90% случаев будут отличаться шрифты

# В контейнере:
# Запуск
docker run --rm --volume "$(pwd)/example/1":/app/submission -it $(docker build --quiet .) npm run unit

# Отладка
docker run --rm --volume "$(pwd)/example/1":/app/submission -it $(docker build --quiet .) /bin/bash
```

Порядок работы:
1. На вход jest подается рабочая папка
2. В папке должны содержаться (см. примеры):
   1. Сам тест написанный с использованием библиотеки jest `test.js`
   2. Снапшоты если они имеются `__snapshots__`
   3. Если используется `npm run unit`, то предполагается, что решение пользователя уже встроено в файл `test.js`
   4. Если используется `npm run comparing`, то обязательно должен быть файл с решением от пользователя `solution/index.html` (и дополнительные файлы, если есть)
3. Выход jest подается в stdout
4. В случае неуспешного интеграционного теста, дополнительно создается папка `__diff__` в которой в виде изображений представлены ошибки пользователя. 

Предполагаемый workflow админа:
1. Админ добавляет задачу и предполагаемое решение в админке 
2. Эта задача запускается в контейнере через npm run unit/integration (без снапшотов)
3. В итоге тест должен отработать успешно и может появиться папка __snapshots__. Это эталонный снапшот/скриншот с которым будет сравниваться решение пользователя. Его надо запомнить
