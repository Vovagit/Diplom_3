# Diplom_3

# Запуск chrome

```bash
mvn test
```

# Запуск яндекс браузера

```bash
mvn -Dbrowser=yandex -Ddriver.version=122.0.6261.128 -Dwebdriver.yandex.bin=C:\\Users\\tryca\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe test
```

# Просмотр отчета

```bash
allure serve target/allure-results
```