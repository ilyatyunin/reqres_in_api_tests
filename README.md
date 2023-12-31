# Проект по автоматизации API-запросов для сайта [reqres.in](https://reqres.in/)
reqres.in — является тренировочным ресурсом для взаимодействия с API.

## :pushpin: Содержание:

- [Используемый стек](#-используемый-стек)
- [Список тестов](#heavy_check_mark-список-тестов)
- [Запуск автотестов](#arrow_forward-запуск-автотестов)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Пример Allure-отчета](#-пример-allure-отчета)
- [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)
- [Интеграция с Jira](#-интеграция-с-jira)
- [Уведомления в Telegram](#-уведомления-в-telegram)
## &#129470; Используемый стек

<p align="center">
<a href="https://www.jetbrains.com/">
<img width="6%" title="IntelliJ IDEA" src="media/logo/Intelij_IDEA.svg">
</a>
<a href="https://www.java.com/">
<img width="6%" title="Java" src="media/logo/Java.svg">
</a>
<a href="https://docs.qameta.io/allure/">
<img width="6%" title="Allure Report" src="media/logo/Allure_Report.svg">
</a>
<a href="https://qameta.io/">
<img width="5%" title="Allure TestOps" src="media/logo/AllureTestOps.svg">
</a>
<a href="https://gradle.org/">
<img width="6%" title="Gradle" src="media/logo/Gradle.svg">
</a>
<a href="https://junit.org/junit5/">
<img width="6%" title="JUnit5" src="media/logo/JUnit5.svg">
</a>
<a href="https://rest-assured.io/">
<img width="6%" title="Rest-Assured" src="media/logo/RestAssured.png">
</a>
<a href="https://github.com/">
<img width="6%" title="GitHub" src="media/logo/GitHub.svg">
</a>
<a href="https://www.jenkins.io/">
<img width="6%" title="Jenkins" src="media/logo/Jenkins.svg">
</a>
<a href="https://web.telegram.org/">
<img width="6%" title="Telegram" src="media/logo/Telegram.svg">
</a>
<a href="https://www.atlassian.com/ru/software/jira">
<img width="5%" title="Jira" src="media/logo/Jira.svg">
</a>
</p>

- Тесты в данном проекте написаны на языке <code>Java</code> с использованием фреймворка <code>Rest-Assured</code>
- В качестве сборщика использован <code>Gradle</code>
- В качестве фреймворка модульного тестирования задействован <code>JUnit 5</code>
- Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота
- Осуществлена интеграция с <code>Allure TestOps</code> и <code>Jira</code>

## :heavy_check_mark: Список тестов

- [x] Регистрация аккаунта: POST REGISTER
- [x] Получение списка пользователей: GET LIST USERS 
- CRUD-операции с пользователем:
- [x] Создание пользователя POST CREATE
- [x] Получение пользователя GET SINGLE USER
- [x] Обновление данных пользователя PUT UPDATE
- [x] Удаление пользователя DELETE

## :arrow_forward: Запуск автотестов
### Варианты запуска тестов
- ```test``` — Запуск всех тестов
- ```positive_test``` — Запуск позитивных тестов
- ```negative_tests``` — Запуск негативных тестов
### Запуск тестов локально из терминала
```
gradle clean test
```

## <img width="4%" style="vertical-align:middle" title="Jenkins" src="media/logo/Jenkins.svg"> [Сборка в Jenkins](https://jenkins.autotests.cloud/job/api_reqres/)
### Параметры сборки
* <code>TASK</code> — выбор запуска тестов. Значение по-умолчанию — <code>chrome</code> — запуск всех тестов.

<p align="center">
<img title="Jenkins Build" src="media/screens/JenkinsBuild.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="media/logo/Allure_Report.svg"> [Пример Allure-отчета](https://jenkins.autotests.cloud/job/api_reqres/14/allure/)
<p align="center">
<img title="Allure Overview" src="media/screens/AllureOverview.png">
</p>

### Результат выполнения теста содержит:

* Request с вложением ```Метод запроса, Адрес, Headers, Body, Curl```
* Response с вложением ```Status code, Headers, Body```

<p align="center">
<img title="Test Results in Alure" src="media/screens/AllureSuites.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure TestOps" src="media/logo/AllureTestOps.svg"> [Интеграция с Allure TestOps](https://allure.autotests.cloud/project/3607/launches)
На вкладке <code>Dashboards</code> отображается:
- Количество тест-кейсов и их статус
- Соотношение ручных/автоматизированных тестов
- Результаты запусков/прохождения тестов в графике по датам
<p align="center">
<img title="Allure TestOps DashBoard" src="media/screens/AllureDashboards.png">
</p>

На вкладке <code>Launches</code> можно увидеть результаты запусков автоматизированных тестов
<p align="center">
<img title="Allure TestOps DashBoard" src="media/screens/LaunchesTestOps.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Jira" src="media/logo/Jira.svg"> [Интеграция с Jira](https://jira.autotests.cloud/browse/HOMEWORK-844)
Реализована интеграция <code>Allure TestOps</code> с <code>Jira</code>. В задаче отображаются прикреплённые к ней тест-кейсы, а также результаты запусков/прохождения тестов.
<p align="center">
<img title="Allure TestOps DashBoard" src="media/screens/Jira.png">
</p>

### <img width="4%" style="vertical-align:middle" title="Telegram" src="media/logo/Telegram.svg"> Уведомления в Telegram
После завершения сборки, бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с результатом.
<p align="center">
<img title="Allure TestOps DashBoard" src="media/screens/Telegram.png" width="50%" height="50%">

</p>
