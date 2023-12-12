package api.Lesson_16.test;

import api.Lesson_16.Specification;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestEchoTest {
    private final static String URL = "https://postman-echo.com";
    @Test
    public void postRequestTest() {
        Specification.installSpecification(Specification.requestSpec(URL));
        given()
                    .basePath("/post")
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .formParam("foo1", "bar1")
                    .formParam("foo2", "bar2")
                .when()
                    .post()
                .then().log().body()
                    .statusCode(200)
                    .body("form.foo1", equalTo("bar1"))
                    .body("form.foo2", equalTo("bar2"));
    }
}
