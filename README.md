Для запуска используется команда: mvn clean test site
Для запуска в debug-режиме:  mvn clean test -DforkCount=0
При запуске appium серевра на вкладке "Экспертный" указать "переопределять сессию"
После запуска Allure-отчет будет доступен в папке проекта target/site/allure-maven-plugin/index.html
Устройства для запуска определябтся в файле devices.csv


Ручные smoke-сценарии находятся по пути src/test/resources/Smoke тесты m.bazaraki.com.xlsx

Что хотел сделать и не успел: запуск appium сервера не вручную, а из проекта