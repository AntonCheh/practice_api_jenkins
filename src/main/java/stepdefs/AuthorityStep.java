package stepdefs;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class AuthorityStep {

    Response response;

    @Когда("я отправляю POST запрос на {string} с телом:")
    public void яОтправляюPOSTЗапросНаСТелом(String url, Map<String, String> requestBody) {
        response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post(url);
        System.out.println(requestBody);
    }

    @Тогда("я получаю успешный ответ с кодом {int}")
    public void яПолучаюУспешныйОтветСКодом(int expectedStatusCode) {
        Assert.assertEquals("статус код не соответствует",
                expectedStatusCode, response.getStatusCode());
    }

    @И("ответ содержит id и токен")
    public void ответСодержитIdИТокен() {
        Integer id = response.jsonPath().getInt("id");
        String token = response.jsonPath().getString("token");

        Assert.assertNotNull("id must exist", id);
        Assert.assertNotNull("Токен должен присутсвовать в ответе", token);

    }


    @Тогда("я получаю ответ с кодом {int}")
    public void яПолучаюОтветСКодом(int expectedStatusCode) {
        Assert.assertEquals("код не соответствует",
                expectedStatusCode, response.getStatusCode());
    }


    @И("ответ содержит сообщение об ошибке {string}")
    public void ответСодержитСообщениеОбОшибке(String expectedMessage) {
        String errorMessage = response.jsonPath().getString("error");
        Assert.assertEquals("Сообщение об ошибке на совпадает",
                expectedMessage, errorMessage);
    }


}
