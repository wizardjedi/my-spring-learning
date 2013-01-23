Part 0.7 Spring profiles

Создано простое приложение с разделением по профилям.

Созданы две категории профилей:
 * профили источников данных (`mysql`,`postgresql`)
 * профили для развёртывания (`test`,`devel`,`production`)

= Сборка проекта

```
$ mvn clean install
```

= Запуск приложения

Для запуска по умолчанию: devel профиль и postgresql

```
$ mvn exec:java -Dexec.mainClass="learn.sprofile.App"
```

Например, тестовый профиль и mysql

```
$ mvn exec:java -Dexec.mainClass="learn.sprofile.App" -Dspring.profiles.active="test,mysql"
```
