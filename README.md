# ClientRequestManagement
Веб сервис написан с помощью **Spring Boot**.

Сборщик проекта - **Maven**.

При разработке использовалась - **Intellij Idea**.

Для запуска проекта нужно выполнить команды **Maven** `mvn clean install`. После запустить созданный мавеном *.jar файл.
Или с помощью IDE запустить `ClientRequestManagementApplication.java`.

Веб сервис принимает запросы по порту: **8080**.

## Описание веб сервиса
### Список всех клиентов
Метод - **GET**.

Endpoint - `/clients`.

Пример ответа:
```
[
    {
        "id": 1,
        "phone": "87078515441",
        "fio": "Shauyenov Aknur",
        "organization": "Suleymen Demirel University Kaskelen",
        "bin": "640320302154",
        "abbr": "SDUK",
        "hashCode": 669857729
    },
    {
        "id": 2,
        "phone": "87078515441",
        "fio": "Name Surname",
        "organization": "Suleymen Demirel University Kaskelen",
        "bin": "640320302154",
        "abbr": "SDUK",
        "hashCode": 1698912912
    }
]
```
### Просмотр клиента по ID
Метод - **GET**.

Endpoint - `/clients/{id}`.

Пример ответа:
```
{
    "id": 1,
    "phone": "87078515441",
    "fio": "Shauyenov Aknur",
    "organization": "Suleymen Demirel University Kaskelen",
    "bin": "640320302154",
    "abbr": "SDUK",
    "hashCode": 669857729
}
```
### Создание нового клиента
Метод - **POST**.

Endpoint - `/clients`.

Пример запроса:
```
{
	"phone": "87078515441",
	"fio": "Name Surname",
	"organization": "Suleymen Demirel University Kaskelen",
	"bin": "640320302154"
}
```
Пример ответа:
```
{
    "id": 2,
    "phone": "87078515441",
    "fio": "Name Surname",
    "organization": "Suleymen Demirel University Kaskelen",
    "bin": "640320302154",
    "abbr": "SDUK",
    "hashCode": 1698912912
}
```
При попытке отправить одного и того же клиента сервис возвращает ошибку: **This client already has been inserted**. А при попытке отправить не валидный телефонный номер сервис возвращает ошибку: **The phone number is invalid**.
### Редактировать клиента
Метод - **PUT**.

Endpoint - `/clients/{id}`.

Сервис сначала пытается найти клиента по заданнаму ID. Если он не находит то создает новый. Ошибки которые могут возникнуть при создании возможны и здесь.

Пример запроса:
```
{
	"phone": "87057777797",
	"fio": "Name Surname",
	"organization": "Zhambyl Zharyk Sauda",
	"bin": "640320302154"
}
```
Пример ответа:
```
{
    "id": 2,
    "phone": "87057777797",
    "fio": "Name Surname",
    "organization": "Zhambyl Zharyk Sauda",
    "bin": "640320302154",
    "abbr": "ZZS",
    "hashCode": 1774324301
}
```
### Удаление клиента
Метод - **DELETE**.

Endpoint - `/clients/{id}`.

Удаляет клиента по ID.
