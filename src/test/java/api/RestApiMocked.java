package api;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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


}
