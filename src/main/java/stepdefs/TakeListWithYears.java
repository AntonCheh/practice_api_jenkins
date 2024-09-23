package stepdefs;

import groovy.lang.IntRange;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class TakeListWithYears {

    Response response;

    @Когда("я отправляю GET запрос на {string}")
    public void яОтправляюGETЗапросНа(String url) {
        response = RestAssured.get(url);
    }

//    @Тогда("я получаю успешный ответ с кодом {int}")
//    public void яПолучаюУспешныйОтветСКодом(int expectedStatusCode) {
//        Assert.assertEquals("статус код не соответствует",
//                expectedStatusCode, response.getStatusCode());
//    }


    @И("ответ содержит список ресурсов")
    public void ответСодержитСписокРесурсов() {
        List<?> resources = response.jsonPath().getList("data");
        Assert.assertFalse("list does not to be empty", resources.isEmpty());
        System.out.println(resources);
    }


    @И("ресурсы отсортированы по годам")
    public void ресурсыОтсортированыПоГодам() {
        List<Integer> years = response.jsonPath().getList("data.year", Integer.class);
        for (int i = 0; i < years.size() - 1; i++) {
            Assert.assertTrue("Годы должны быть отсортированы по возрастанию",
                    years.get(i) <= years.get(i + 1));
        }
        System.out.println(years);
    }
}


