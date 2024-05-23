# Обновление Runner

**_Полное название версии: 2.1.5_**

**_Сокращенное название версии: 215_**

**_Тип версии: Stable Release_**

**_Тип RAPI: RAPI Developer_**

---------------------------------------------------

>**_Для работы RAPI (1.4) вам потребуется JDK 17_**

**_Пароль от установщика: runner215_**

**_Подробная документация: [видео](https://www.youtube.com)_**

----------------------------------------------------

| R | S | C |
| :--: | :-: | :-: |
| **Runner** | **Source** | **Code** |

| R | C | F |
| :--: | :-: | :-: |
| **Runner** | **Config** | **File** |

---------------------------------------------------

## RPM - Runner Package Manager
**_С помощью RPM вы можете устанавливать библиотеки из интернета_**

**_Что бы установить библиотеку в Shell: --rpm install [Имя библиотеки]_**

**_Что бы удалить библиотеку в Shell: --rpm uninstall [Имя библиотеки]_**

**_Если вы хотите получить пример по библиотеки в Shell: --rpm install examples [Имя библиотеки] [Имя файла].rsc_**

**_Все библиотеки и примеры вы можете расмотреть на [нажми](https://github.com/Blaze-Group/Runner/tree/v2.5/package-manager)_**'

**_Если вы хотите отправить свою библиотеку в RPM напишите [Chat](https://t.me/+ZYfCSh6n3y0zYTY6)_**

# Синтаксис
**_Для вывода текста:_**
```cpp
out("Hello, World!")
```
**_Что бы вывести текст на новой строке:_**
```cpp
outline("Hello, World!")
```
**_Или же:_**
```cpp
out("Hello, World!\n")
```
**_Для создания переменных:_**
```cpp
text = "Hello, World!"
```
**_Также:_**
```csharp
// Данное создание не стабильно!
var text = "Hello, World!"
```
**_Для создание функции:_**
```cpp
void Main() {
  outline("Hello, World!")
}

Main()
```
**_Для создание пользовательского ввода:_**
```cpp
inputInt = toInt(Input("Age: "))
inputStr = toString(Input("Name: "))
```
**_Для создание проверки:_**
```cpp
IF (2 > 0) {
  outline("2 > 0")
} ELSE IF (2 < 0) {
  outline("2 < 0")
} ELSE {
  outline("ELSE")
}
```
**_Конвертеры:_**
```java
int = 1
str = "1"
outline(toString(int))
outline(toInt(str))
```

**_Что бы узнать подробнее перейдите на подробную докуметацию_**

## Добавление других скриптов
```cpp
include "SCRIPT_NAME"
```

**_Если скрипт не находится в Lib/User Module или в Lib/. Нужно писать польный путь_**

**_Скрипт должен иметь расшерение .rsc, так как расшерение в include не пишится!_**

# IDE
>IDE В разработке!

# License

>MIT License

Copyright (c) 2024 Blaze

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## The end :)
