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
}
