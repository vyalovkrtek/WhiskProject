import core.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestTests {

    RequestSpecification request = given()
            .contentType(ContentType.JSON)
            .header("Authorization","Bearer "+Config.token);
    public Map<String,String> postMap = new LinkedHashMap<>();

    @BeforeTest
    public void precondition(){
        Response postResponse = request
                .accept(ContentType.JSON)
                .body("{\"name\": \"shopList\", \"primary\": false}")
                .when()
                .post(Config.host+"/list/v2");
        Assert.assertEquals(postResponse.statusCode(), 200);
        postMap = postResponse.then().extract().path("list");
    }

    @Test
    public void test1(){
        Response getResponse = request
                .accept(ContentType.JSON)
                .when()
                .get(Config.host+"/list/v2/"+postMap.get("id"));

        Assert.assertEquals(getResponse.statusCode(), 200);
        Map<String,String> getMap = getResponse.then().extract().path("list");
        Assert.assertEquals(getMap.get("id"), postMap.get("id"));
        getMap = getResponse.then().extract().path("content");
        Assert.assertTrue(getMap.isEmpty());
    }

    @Test
    public void test2(){
        Response deleteResponse = request
                .accept(ContentType.JSON)
                .delete(Config.host+"/list/v2/"+postMap.get("id"));

        Assert.assertEquals(deleteResponse.statusCode(), 200);

        Response getResponse = request
                .accept(ContentType.JSON)
                .when()
                .get(Config.host+"/list/v2/"+postMap.get("id"));
        Assert.assertEquals(getResponse.statusCode(), 200);//test will fail here, because it returns 400 code, but in task you said that "4. Verify that code reeponse = 200". Maybe it needs to be failed?
        Assert.assertEquals(getResponse.then().extract().path("code"), "shoppingList.notFound");

    }
}
