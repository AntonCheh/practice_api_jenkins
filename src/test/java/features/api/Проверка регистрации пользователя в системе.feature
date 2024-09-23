#language: ru

@api_2.2 @reqres
Функционал: Регистрация пользователя в системе ReqRes

  Сценарий: Успешная регистрация пользователя
    Когда я отправляю POST запрос на "https://reqres.in/api/register" с телом:
      | email              | eve.holt@reqres.in |
      | password           | pistol   |

    Тогда я получаю успешный ответ с кодом 200
    И ответ содержит id и токен

  Сценарий: Неудачная регистрация пользователя без пароля
    Когда я отправляю POST запрос на "https://reqres.in/api/register" с телом:
      | email           | eve.holt@reqres.in |
      | password |         |
    Тогда я получаю ответ с кодом 400
    И ответ содержит сообщение об ошибке "Missing password"