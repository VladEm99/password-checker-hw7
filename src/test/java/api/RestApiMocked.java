package api;
import static io.restassured.RestAssured.*;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import dto.OrderDtoMocked;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.RandomDataGeneration;

public class RestApiMocked {
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI ="http://35.208.34.242";
        RestAssured.port = 8080;
    }

    @Test
    public void getOrderByIdAndResponseCodeIsOk(){
        get("/test-orders/1")
                .then()
                .log()
                .all()
                .statusCode(200);
    }
    @Test
    public void getOrderByInvalidIdAndResponseCodeIsBadRequest(){
        get("/test-orders/105")
                .then()
                .log()
                .all()
                .statusCode(400);
    }
    @Test
    public void getAllOrdersAndResponseCodeIsOk(){
        get("/test-orders/get_orders")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void deleteOrderWithValidIdAndResponseCodeIsOk(){
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/5")
                .then()
                .log()
                .all()
                .statusCode(204);

    }
    @Test
    public void deleteOrderWithInvalidIdAndResponseCodeIs404(){
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/11")
                .then()
                .statusCode(404);
    }
    @Test
    public void deleteOrderRequestWithInvalid8DigitKeyAndResponseCodeIs401(){
        given()
                .log()
                .all()
                .when()
                .header("api_key", "45678541")
                .delete("/test-orders/5")
                .then()
                .statusCode(401);
    }
    @Test
    public void deleteOrderRequestWithInvalid16CharsKeyAndResponseCodeIs401(){
        given()
                .log()
                .all()
                .when()
                .header("api_key", "hgthQWERzxcvPORQ")
                .delete("/test-orders/5")
                .then()
                .statusCode(401);
    }
    @Test
    public void deleteOrderRequestWithoutApiKeyAndResponseCodeIs401(){
        delete("/test-orders/5")
                .then()
                .statusCode(400);
    }
    @Test
    public void deleteOrderWithInvalidIdCharInsteadOfIntegerAndResponseCodeIs404(){
        given()
                .log()
                .all()
                .when()
                .header("api_key", "1234567890123456")
                .delete("/test-orders/Hi")
                .then()
                .statusCode(404);
    }
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
    @ParameterizedTest
    public void getOrderByIdAndResponseCodeIsOk(int id){
        given()
                .log()
                .all()
                .when()
                .get("/test-orders/{id}", id)
                .then()
                .log()
                .all()
                .statusCode(200);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,5,9,10})
    public void getOrdersByIdAndCheckResponseCodeIsOk(int orderId) {
        int responseOrderId =
        given().
                log()
                .all()
                .when()
                .get("/test-orders/{orderId}", orderId)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .path("id");
        Assertions.assertEquals(orderId,responseOrderId);
    }
    @ParameterizedTest
    @ValueSource(ints = {-1, 11, 25})
    public void getOrdersByInvalidIdAndCheckResponseCodeIsBadRequest(int InvalidOrderId) {
        given().
                log()
                .all()
                .when()
                .get("/test-orders/{InvalidOrderId}", InvalidOrderId)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @ParameterizedTest
    @CsvSource({
            "12345, example1",
            "6789, example2",
            "123456789, example3",
    })
     void testLoginWithUserNameAndPasswordToGetApiKeyAndStatusCodeIsOk(String username, String password){
        given()
                .queryParam("username", username)
                .queryParam("password", password)
                .log()
                .all()
                .when()
                .get("/test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void testGetRequestWithoutUsernameAndPasswordAndStatusCodeIsBadRequest(){
        given()
                .log()
                .all()
                .when()
                .get("/test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }



    @Test
    public void createOrderAndCheckResponseCodeIsOk(){
        //creating new Faker instance
        Faker faker = new Faker();
        OrderDtoMocked orderDtoMocked = new OrderDtoMocked();

        orderDtoMocked.setStatus("OPEN");
        orderDtoMocked.setCourierId(faker.number().numberBetween(1,1000));
        orderDtoMocked.setCustomerName(faker.name().fullName());
        orderDtoMocked.setCustomerPhone(faker.phoneNumber().cellPhone());
        orderDtoMocked.setComment(faker.lorem().sentence());
        orderDtoMocked.setId(faker.number().numberBetween(1,1000));


        given()
                .header("Content-Type", "application/json")
                .log()
                .all()
                .when()
                .body(new Gson().toJson(orderDtoMocked))
                .post("/test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,5,9,10})
    public void updateOrderMockedAndCheckResponseCodeIsOk(int orderId){

        //creating new Faker instance
        Faker faker = new Faker();

        OrderDtoMocked orderDtoMocked = new OrderDtoMocked();

        orderDtoMocked.setStatus("OPEN");
        orderDtoMocked.setCourierId(faker.number().numberBetween(1,1000));
        orderDtoMocked.setCustomerName(faker.name().fullName());
        orderDtoMocked.setCustomerPhone(faker.phoneNumber().cellPhone());
        orderDtoMocked.setComment(faker.lorem().sentence());
        orderDtoMocked.setId(faker.number().numberBetween(1,1000));


        given()
                .header("Content-Type", "application/json")
                .header("api_key", "1234567890123456")
                .log()
                .all()
                .when()
                .body(new Gson().toJson(orderDtoMocked))
                .put("/test-orders/{orderId}", orderId)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK);
    }



}
