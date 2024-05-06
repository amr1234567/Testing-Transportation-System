package amr.AdminTesting;

import Constants.ApiForAdminConstants;
import Constants.ApiForManagerConstants;
import Models.Response.Bus;
import Models.Response.ResponseModel;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Admin_GetBuses_Testing {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://transportationsystem.somee.com/";
    }

    @Test(priority = 4)
    public void GetBuses_WithTokenSet(){
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .get(ApiForAdminConstants.GetAllBusesEndPoint_Admin);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        ResponseModel<Bus[]> responseBody = res.as(new TypeRef<ResponseModel<Bus[]>>() {});
        Assert.assertNotNull(responseBody.body);
    }
    @Test(priority = 4)
    public void GetBuses_WithNoToken(){
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiForManagerConstants.GetAllJourneysEndPoint_Manager);
        Assert.assertEquals(res.getStatusCode(), 401);
    }
}
