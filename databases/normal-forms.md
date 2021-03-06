# Нормальные формы баз данных

[Ссылка на источник](https://www.youtube.com/watch?v=zqQxWdTpSIA&ab_channel=ListenIT)
[Ссылка на хорошую книгу](https://postgrespro.ru/media/2017/08/09/sqlprimer.pdf)

> Реляционная БД - это упорядоченная информация, связанная между собой определенными отношениями.Такие базы состоят из таблиц, в которых и содержится информация.

> Нормализация - метод проектирования базы данных, который позволяет привести БД к минимальной избыточности.

## Зачем нужна нормализация?

- Устранения аномалий;
- Повышения производительности;
- Повышения удобства управления даннымм.

Избыточность данных - это когда одни и те же данные храняться в нескольких местах базы, что приводит к аномалиям.

| id  | Наименование | Материал      |
| --- | ------------ | ------------- |
| 1   | Стул         | Металл        |
| 2   | Стол         | Массив дерева |
| 3   | Кровать      | ЛДСП          |
| 4   | Шкаф         | Массив дерева |
| 5   | Комод        | ЛДСП          |

Что же не так с этой таблицей?

Наименование и материал повторяются и для изменения требуется менять много строк таблицы. Возникают аномалии, которые не позволяют таблице оставаться консистентной.

Стоило сделать по-другому и внести в исходную таблицу идентификатор материала, который бы однозначно характеризовал каждую строку таблицы без повторений.

| id  | Наименование | Идентификатор материала |
| --- | ------------ | ----------------------- |
| 1   | Стул         | 2                       |
| 2   | Стол         | 1                       |
| 3   | Кровать      | 3                       |
| 4   | Шкаф         | 1                       |
| 5   | Комод        | 3                       |


| Идентификатор материала | Материал      |
| ----------------------- | ------------- |
| 1                       | Массив дерева |
| 2                       | Металл        |
| 3                       | ЛДСП          |

## Процесс нормализации

> Следyя определенным правилам и соблюдая определенные требования проектируются таблицы в базе данных. Правила группируются в наборы, и если база соответствует опредленному набору правил, то она находится в определенной нормальной форме.

> Нормальная форма базы данных - это набор правил и критериев, которым должна отвечать БД.

> Процесс нормализации - это последовательный процесс приведения базы данных к эталонному виду (переход от одной нормальной формы к следующей).

При этом жесткость накладываемых ограничений растет с наложением все новой формы.

Полный порядок нормальных форм:

1. Ненормальная форма или нулевая нормальная фоорма (UNF)
2. Первая нормальная форма (1NF)
3. Вторая нормальная форма (2NF)
4. Третья нормальная форма (3NF)
5. Нормальная форма Бойса-Кодда (BCNF)
6. Четвертая нормальная форма (4NF)
7. Пятая нормальная форма (5NF) 
8. Доменно-ключевая нормальная форма (DKNF)
9. Шестая нормальная форма (6NF)

Разберем каждую (или почти каждую, так как на практике нормализация до третьей нормальной формы считается достаточной) нормальную форму отдельно.

## Нулевая нормальная форма (UNF)

Обычно эту форму, как нормальную не выделяют, но лучше знать про нее, чем не знать. Здесь она рассмотрена, чтобы показать, какие БД являются реляционными, а какие нет.

 Перед приведением БД к нормальной формы нужно привести её к табличному виду так, чтобы он отвечал базовым принципам реляционной теории.

> Строки в таблицах не должны быть пронумерованы (порядок строк и столбцов не имеет значения).

В реляционных БД невозможно обращение к какой-то конкретной ячейке таблицы по ее номеру строки и столбцу (грубо говоря это не двумерный массив, а что-то иное)

Рассмотрим небольшой пример обычной таблицы Excel:

| Порядковый номер | А      | B       |
| ---------------- | ------ | ------- |
| 1                | Иван   | Иванов  |
| 2                | Петр   | Зюзин   |
| 3                | Сергей | Сергеев |
| 4                | Антон  | Семин   |
| 5                | Боб    | Сноу    |

Что не так с этой таблицей?

По сути тут есть порядковый номер, однако такая таблица не является реляционной, так как, если мы поменяем строки местами, то наша нумерация просто нарушится

Поэтому стоит удалить порядковый номер и дать столбцам нормальные имена


| last_name | first_name |
| --------- | ---------- |
| Иван      | Иванов     |
| Петр      | Зюзин      |
| Сергей    | Сергеев    |
| Антон     | Семин      |
| Боб       | Сноу       |


Теперь можно сказать, что данная таблица не нарушает базовые принципы реляционной теории и можно начинать приводить её к первой нормальной форме

## Первая нормальная форма (1NF)

Требование к первой нормальной форме очень простое и заключается в следующем:

> Таблицы должны соответствовать реляционной модели данных и соблюдать определенные реляционные принципы:

- В таблице не должно быть дублирующих строк;
- В каждой ячейке хранится атомарное значение (одно не составное значение);
- В столбце хранятся данные одного типа;
- Отсутствуют массивы и списки в любом виде.

Следующая таблица не находится даже в первой нормальной форме

| Сотрудник    | Контакт                                         |
| ------------ | ----------------------------------------------- |
| Иван. А. К.  | 123-345-422, 123-123-412                        |
| Петр Ж. Б.   | 345-123-435, 546-745-856                        |
| Сергей П. О. | Рабочий тел: 321-553-654, Домашний: 969-493-343 |
| Антон С. Ф.  | 434-245-566, 555-35-35                          |
| Боб  Ж. Ж.   | 969-69-69                                       |
| Боб  Ж. Ж.   | 969-69-69                                       |

Тут в ячейках хранятся повторяющиеся значения, а в контактах есть номера, которые хранятся через запятую.

Чтобы привести данную таблицу к первой нормальной форме, необходимо удалить повторящиеся строки и вынести тип телефона в отдельный столбец.

Тогда данная таблица в первой нормальной форме будет иметь следующий вид:

| Сотрудник    | Контакт                  | Тип телефона |
| ------------ | ------------------------ | ------------ |
| Иван. А. К.  | 123-345-422, 123-123-412 |              |
| Петр Ж. Б.   | 345-123-435, 546-745-856 |              |
| Сергей П. О. | 321-553-654              | Рабочий      |
| Сергей П. О. | 969-493-343              | Домашний     |
| Антон С. Ф.  | 434-245-566, 555-35-35   |              |
| Боб  Ж. Ж.   | 969-69-69                |              |

Таким образом главное правило первой нормальной формы:

- Назначение строк - хранить данные;
- Назначение столбцов - хранить структурную информацию;
- Назначение ячеек - хранить атомарное значени.

> Если таблица создана с соблюдением всех реляционных принципов, значит она уже находится в первой нормальной форме, по сути все реляционные базы данных по умолчанию находятся в первой нормальной форме.



## Вторая нормальная форма (2NF)

Требования, необходимые для перехода таблицы во вторую нормальную форму:

- Таблица находится в первой нормальной форме
- Таблица должна иметь ключ
- Все неключевые столбцы таблицы должны зависеть от полного ключа (в случае, если ключ составной)

Ключ - это столбец или набор столбцов, по которым можно гарантированно отличить строки друг от друга, то есть ключ идентифецирует каждую строку таблицы. По ключу мы можем обратиться к конкретной строке в таблице.

> Если ключ состоит из нескольких столбцов, то все остальные неключевые столбцы должны зависеть от всего ключа (т. е. от всех столбцов в этом ключе).

Стоит также отметить, что первичный ключ, если он является составным, не должен быть избыточным, т. е. никакое подмножество аттрибутов, входящих в него, не должно обладать свойством уникальности.

Ключи нужны для адресации на уровне строк (записей). При наличии в таблице более
одного потенциального ключа один из них выбирается в качестве так называемого
первичного ключа, а остальные будут являться альтернативными ключами

В таблице не должно быть данных, которые можно получить, зная только часть ключа, т. е. только один столбец составного ключа.

Таблица, содержащая внешний ключ, называется ссылающейся таблицей (referencing table). Таблица, содержащая соответствующий потенциальный ключ, называется ссылочной (целевой) таблицей (referenced
table). 

Основное правило второй формы:

> Таблица должна иметь правильный ключ, по которому можно идентифецировать каждую строку.

Представим, что нам нужно хранить список сотрудников организации, для чего мы создали следующую таблицу:

| ФИО          | Должность   | Подразделение    | Описание подразделения            |
| ------------ | ----------- | ---------------- | --------------------------------- |
| Иван. А. К.  | Программист | Отдел разработки | Разработка и сопровождение сайтов |
| Петр Ж. Б.   | Бухгалтер   | Бухгалтерия      | Ведение бух. учета                |
| Сергей П. О. | Продавец    | Отдел реализации | Организация сбыта продукции       |

Видно, что данная таблица уже находится в первой нормальной форме, так как в ней нет дублирующих строк и все значения атомарны.

Теперь можно начать прцоесс нормализации до второй нормальной формы.

**Для чего нам нужно это сделать?**

Нужно ввести первичный ключ, чтобы однозначно иденцифицировать каждую запись в таблице.

Для этого добавим целочисленный первичный ключ, который будет увеличиваться при добавлении новых записей в базу данных.

Так будет выглядеть таблица, приведенная во второую нормальную форму:

| id  | ФИО          | Должность   | Подразделение    | Описание подразделения            |
| --- | ------------ | ----------- | ---------------- | --------------------------------- |
| 1   | Иван. А. К.  | Программист | Отдел разработки | Разработка и сопровождение сайтов |
| 2   | Петр Ж. Б.   | Бухгалтер   | Бухгалтерия      | Ведение бух. учета                |
| 3   | Сергей П. О. | Продавец    | Отдел реализации | Организация сбыта продукции       |

Теперь рассмотрим более сложную ситуацию, в которой первичный ключ будет составным

| Название проекта     | Участник     | Должность   | Срок проекта (мес) |
| -------------------- | ------------ | ----------- | ------------------ |
| Внедрение приложения | Иван. А. К.  | Программист | 8                  |
| Внедрение приложения | Сергей П. О. | Бухгалтер   | 3                  |
| Внедрение приложения | Петр Ж. Б.   | Менеджер    | 12                 |
| Открытие магазина    | Сергей П. О. | Бухгалтер   | 12                 |
| Открытие магазина    | Петр Ж. Б.   | Менеджер    | 12                 |

Представим, что какая-то организация выполняет несколько проектов, в которых могут быть задействованы несколько участников и мы хотим хранить информацию об этих проектах. В частности мы хотим знать, кто участвует в каждом из проектов, продолжительность и т. д. Также предполагается, что сотрудник может присутствовать в нескольких проектах. Для хранения таких данных подходит таблица, описанная выше (находится в первой нормальной форме).

Первичного ключа в данной таблице нет, но четко определить каждую строку мы может только по комбинации стобцов, например **название проекта + участник**. Иными словами, зная название проекта и участника мы можем **четко определить** столбец в таблице, так как сочетание этих столбцов является уникальным. Таким образом мы определили составной ключ. 

| Название проекта         | Участник         | Должность   | Срок проекта (мес) |
| ------------------------ | ---------------- | ----------- | ------------------ |
| **Внедрение приложения** | **Иван. А. К.**  | Программист | 8                  |
| **Внедрение приложения** | **Сергей П. О.** | Бухгалтер   | 8                  |
| **Внедрение приложения** | **Петр Ж. Б.**   | Менеджер    | 8                  |
| **Открытие магазина**    | **Сергей П. О.** | Бухгалтер   | 12                 |
| **Открытие магазина**    | **Петр Ж. Б.**   | Менеджер    | 12                 |

Так как первичный ключ составной, необходимо также проверить и второе условие о том, что все неключевые столбцы таблицы должны зависеть от полного ключа.

Чтобы это проверить, нужно задать несколько вопросов:

- Можем-ли мы определить должность, зная только название проекта? - Нет, для этого необходимо знать и участника, значит тут все хорошо.
- Можем-ли мы определить должность, зная только участника? - Да, можем, значит наш первичный ключ плохой и требования второй нормальной формы не выполняются.
  
В таком случае необходимо выполнить декомпозицию таблиц.

> Декомпозиция - процесс разбиения одного отношения (таблицы) на несколько.

Чтобы привести нашу базу данных в нормальную форму мы должны созать следующие таблицы:

| id проекта | Название проекта     | Срок проекта |
| ---------- | -------------------- | ------------ |
| 1          | Внедрение приложения | 8            |
| 2          | Открытие магазина    | 12           |


| id участника | Участник    | Должность   |
| ------------ | ----------- | ----------- |
| 1            | Иван. А. К. | Программист |
| 2            | Сергей П. О | Бухгалтер   |
| 3            | Петр Ж. Б.  | Менеджер    |

| id проекта | id участника |
| ---------- | ------------ |
| 1          | 1            |
| 1          | 2            |
| 1          | 3            |
| 2          | 2            |
| 2          | 3            |

Таким образом мы реализовали связь многие-ко-многим между проектами и участниками и привели таблицу во вторую нормальную форму

> Связь многие-ко-многим в данном случае говорит нам о том, что в одном проекте может быть много участников, а участник может работать над несколькими проектами

## Третья нормальная форма (3NF)

Требования, необходимые для перехода таблицы во вторую нормальную форму:

- В таблице должна отсутствовать транзитивная зависимость.

> Транзитивная зависимость - это когда неключевые столбцы зависят от значений других неключевых столбцов.

Неключевые столбцы не должны играть роль ключа в таблице. Таким образом, внимание третьей нормальной формы направлено на неключевые столбцы таблицы, а правило можно также сформировать следующим образом:

**Таблица должна содержать правильные неключевые столбцы**

Иными словами, неключевые стобцы не должны пытаться играть роль ключа в таблице, т. е. такими столбцами, которые не дают возможности получить данные из других столбцов, а дают посмотреть на информацию, которая в них содержится.

Для примера возьмем таблицу с сотрудниками, которая была рассмотрена ранее 

| id  | ФИО          | Должность   | Подразделение    | Описание подразделения            |
| --- | ------------ | ----------- | ---------------- | --------------------------------- |
| 1   | Иван. А. К.  | Программист | Отдел разработки | Разработка и сопровождение сайтов |
| 2   | Петр Ж. Б.   | Бухгалтер   | Бухгалтерия      | Ведение бух. учета                |
| 3   | Сергей П. О. | Продавец    | Отдел реализации | Организация сбыта продукции       |

Чтобы определить, находится-ли данная таблица в третьей нормальной форме, необходимо проверить все неключевые стобцы. Каждый из них должен зависеть только от первичного ключа и никаким образом не относиться к другим неключевым столбцам.

В результате проверки выясняется, что описание подразделения не зависит напрямую от первичного ключа. Чтобы выяснить это, необходимо задать вопрос:

> Каким образом описание подразделения связано с сотрудником? Ответ на вопрос звучит следующим образом: аттрибут описание подразделения содержит детальное описание подразделения, в котором работает сотрудник.

Из этого следует, что описание подразделения напрямую не связано с сотрудником, но связано напрямую со столбцом подразделение, который напрямую связан с сотрудником, ведь сотрудник работает в каком-то конкретном подразделении. Это и есть транзитивная зависимость.

В данном случае один неключевой столбец связан с первичным ключем через другой неключевой столбец.

Чтобы исправить данную проблему необходимо декомпозировать данную таблицу на две: информация о сотруднике и подразделение. Связь таблиц будет осуществляться через внешний ключ

| id  | ФИО          | Должность   | id подразделения (FK) |
| --- | ------------ | ----------- | --------------------- |
| 1   | Иван. А. К.  | Программист | 1                     |
| 2   | Петр Ж. Б.   | Бухгалтер   | 2                     |
| 3   | Сергей П. О. | Продавец    | 3                     |

| id подразделения | Подразделение    | Описание подразделения            |
| ---------------- | ---------------- | --------------------------------- |
| 1                | Отдел разработки | Разработка и сопровождение сайтов |
| 2                | Бухгалтерия      | Ведение бух. учета                |
| 3                | Отдел реализации | Организация сбыта продукции       |

Таким образом мы получили 2 таблицы, которые находятся в 3 нормальной форме и ссылаются друг на друга

В дальнейшем, возможно, напишу и про 4 неполную и полную, но пока так.